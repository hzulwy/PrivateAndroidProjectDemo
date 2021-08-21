package com.brett.hotfixdemo.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

/**
 * @author dell
 */
public class NewClassLoaderInjector {

    private static final class DispatchClassLoader extends ClassLoader{
        private final String mApplicationClassName;
        private final ClassLoader mOldClassLoader;

        private ClassLoader mNewClassLoader;

        private final ThreadLocal<Boolean> mCallFindClassOfLeafDirectly = new ThreadLocal<Boolean>(){
            @Nullable
            @Override
            protected Boolean initialValue() {
                return false;
            }
        };

        DispatchClassLoader(String applicationClassName, ClassLoader oldClassLoader){
            super(ClassLoader.getSystemClassLoader());
            mApplicationClassName = applicationClassName;
            mOldClassLoader = oldClassLoader;
        }

        void setNewClassLoader(ClassLoader classLoader){
            mNewClassLoader = classLoader;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            Log.e("NewClassLoaderInjector","find :"+name);
            if(mCallFindClassOfLeafDirectly.get()){
                return null;
            }
            //1.Application类不需要热修复，使用原本的类加载器获得
            if(name.equals(mApplicationClassName)){
                return findClass(mOldClassLoader,name);
            }
            //2.加载热修复框架的类，因为不需要热修复，就用原本的类加载器获得
            if(name.startsWith("com.enjoy.patch.")){
                return findClass(mOldClassLoader,name);
            }

            return findClass(mNewClassLoader,name);
        }

        private Class<?> findClass(ClassLoader classLoader,String name) throws ClassNotFoundException {
            try {
                mCallFindClassOfLeafDirectly.set(true);
                return classLoader.loadClass(name);
            }finally {
                mCallFindClassOfLeafDirectly.set(false);
            }
        }
    }

    public static ClassLoader inject(Application app, ClassLoader oldClassLoader, List<File>patchs) throws Throwable {
        //分发加载任务的加载器，作为我们自己的加载器的父加载器
        DispatchClassLoader dispatchClassLoader = new DispatchClassLoader(app.getClass().getName(),oldClassLoader);
        //创建自己的加载器
        ClassLoader newClassLoader = createNewCLassLoader(app,oldClassLoader,dispatchClassLoader,patchs);
        dispatchClassLoader.setNewClassLoader(newClassLoader);
        doInject(app,newClassLoader);
        return newClassLoader;
    }

    private static ClassLoader createNewCLassLoader(Context context,ClassLoader oldClassLoader,
                                                    ClassLoader dispatchClassLoader,List<File>patchs) throws Throwable {
        //得到pathList
        Field pathListField = ShareReflectUtil.findField(oldClassLoader,"pathList");
        Object oldPathList = pathListField.get(oldClassLoader);
        //dexElement
        Field dexElementsField = ShareReflectUtil.findField(oldPathList,"dexElements");
        Object[] oldDexElements = (Object[]) dexElementsField.get(oldPathList);

        //Element上得到dexFile
        Field dexFileField = ShareReflectUtil.findField(oldDexElements[0],"dexFile");

        //获得原始的dexPath用于构造classloader
        StringBuilder dexPathBuilder = new StringBuilder();
        String packageName = context.getPackageName();
        boolean isFirstItem = true;
        for (File patch:patchs){
            if(isFirstItem){
                isFirstItem = false;
            }else{
                dexPathBuilder.append(File.pathSeparator);
            }
            dexPathBuilder.append(patch.getAbsolutePath());
        }
        for (Object oldDexElement:oldDexElements){
            String dexPath = null;
            DexFile dexFile = (DexFile) dexFileField.get(oldDexElement);
            if(dexFile != null){
                dexPath = dexFile.getName();
            }
            if(dexPath == null || dexPath.isEmpty()){
                continue;
            }
            if(!dexPath.contains("/"+packageName)){
                continue;
            }
            if(isFirstItem){
                isFirstItem = false;
            }else{
                dexPathBuilder.append(File.pathSeparator);
            }
            dexPathBuilder.append(dexPath);
        }
        final String combinedDexPath = dexPathBuilder.toString();

        //app的native库(so)文件目录，用于构建classloader
        Field nativeLibraryDirectoriesField = ShareReflectUtil.findField(oldPathList,"nativeLibraryDirectories");
        List<File> oldNativeLibraryDirectories = (List<File>) nativeLibraryDirectoriesField.get(oldPathList);

        StringBuilder libraryPathBuilder = new StringBuilder();
        isFirstItem = true;
        for (File libDir : oldNativeLibraryDirectories){
            if(libDir == null){
                continue;
            }
            if(isFirstItem){
                isFirstItem = false;
            }else{
                libraryPathBuilder.append(File.pathSeparator);
            }
            libraryPathBuilder.append(libDir.getAbsolutePath());
        }
        String combinedLibraryPath = libraryPathBuilder.toString();

        //创建自己的类加载器
        ClassLoader result = new PathClassLoader(combinedDexPath,combinedLibraryPath,dispatchClassLoader);
        ShareReflectUtil.findField(oldPathList, "definingContext").set(oldPathList, result);
        ShareReflectUtil.findField(result, "parent").set(result, dispatchClassLoader);
        return result;
    }

    private static void doInject(Application app,ClassLoader classLoader) throws Throwable {
        Thread.currentThread().setContextClassLoader(classLoader);

        Context baseContext = (Context) ShareReflectUtil.findField(app, "mBase").get(app);
        Object basePackageInfo = ShareReflectUtil.findField(baseContext, "mPackageInfo").get(baseContext);
        ShareReflectUtil.findField(basePackageInfo, "mClassLoader").set(basePackageInfo, classLoader);

        if(Build.VERSION.SDK_INT<27){
            Resources res = app.getResources();

            ShareReflectUtil.findField(res,"mClassLoader").set(res,classLoader);
            final Object drawableInflater = ShareReflectUtil.findField(res,"mDrawableInflater").get(res);
            if(drawableInflater != null){
                ShareReflectUtil.findField(drawableInflater,"mClassLoader").set(drawableInflater,classLoader);
            }
        }
    }
}
