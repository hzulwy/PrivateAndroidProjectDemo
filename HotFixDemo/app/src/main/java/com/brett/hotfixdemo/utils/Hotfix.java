package com.brett.hotfixdemo.utils;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 */
public class Hotfix {
    private static final String TAG = "Hotfix";

    public static void installPatch(Application application, File patch){
        //1.获得classloader，pathclassloader
        ClassLoader classLoader = application.getClassLoader();

        List<File> files = new ArrayList<>();
        if(patch.exists()){
            files.add(patch);
        }
        File dexOptDir = application.getCacheDir();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            try {
                NewClassLoaderInjector.inject(application,classLoader,files);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }else {
            try {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    V23.install(classLoader,files,dexOptDir);
                }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    V19.install(classLoader,files,dexOptDir);
                }else {
                    V14.install(classLoader,files,dexOptDir);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static final class V23{

        private static void install (ClassLoader loader,List<File> additionalClassPathEntries,
                                     File optimizedDirectory) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
            //找到pathList
            Field pathListField = ShareReflectUtil.findField(loader,"pathList");
            Object dexPathList = pathListField.get(loader);//拿到loader中pathList的值

            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            //从pathList找到makePathElements方法并执行
            //得到补丁创建的Element[]
            Object[] patchElements = makePathElements(dexPathList,new ArrayList<File>(additionalClassPathEntries),optimizedDirectory,
                    suppressedExceptions);

            //将原本的dexElements与makePathElements生成的数组合并
            ShareReflectUtil.expandFieldArray(dexPathList,"dexElements",patchElements);
            if(suppressedExceptions.size()>0){
                for (IOException e:suppressedExceptions){
                    Log.e(TAG,"Exception in makePathElement",e);
                    throw e;
                }
            }

        }

        //把dex转化为element数组
        private static Object[] makePathElements(Object dexPathList,ArrayList<File>files
                ,File optimizedDirectory,ArrayList<IOException> suppressedExceptions) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            //通过阅读android6、7、8、9源码，都存在makePathElements方法
            Method makePathElements = ShareReflectUtil.findMethod(dexPathList, "makePathElements",
                    List.class, File.class,
                    List.class);
            return (Object[]) makePathElements.invoke(dexPathList, files, optimizedDirectory,
                    suppressedExceptions);
        }
    }

    private static final class V19{
        private static void install(ClassLoader loader,List<File> additionalClassPathentries,
                                    File optimizedDirectory) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
            Field pathListField = ShareReflectUtil.findField(loader,"pathList");
            Object dexPathList = pathListField.get(loader);
            ArrayList<IOException>suppressedExceptions = new ArrayList<>();
            ShareReflectUtil.expandFieldArray(dexPathList,"dexElements",
                    makeDexElements(dexPathList,new ArrayList<File>(additionalClassPathentries),optimizedDirectory,
                            suppressedExceptions));
            if(suppressedExceptions.size()>0){
                for (IOException e:suppressedExceptions){
                    Log.e(TAG,"Exception in makeDexElement",e);
                    throw e;
                }
            }
        }

        private static Object[] makeDexElements(
                Object dexPathList, ArrayList<File> files, File optimizedDirectory,
                ArrayList<IOException> suppressedExceptions)
                throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Method makeDexElements = ShareReflectUtil.findMethod(dexPathList, "makeDexElements",
                    ArrayList.class, File.class,
                    ArrayList.class);


            return (Object[]) makeDexElements.invoke(dexPathList, files, optimizedDirectory,
                    suppressedExceptions);
        }
    }

    /**
     * 14, 15, 16, 17, 18.
     */
    private static final class V14 {


        private static void install(ClassLoader loader, List<File> additionalClassPathEntries,
                                    File optimizedDirectory)
                throws IllegalArgumentException, IllegalAccessException,
                NoSuchFieldException, InvocationTargetException, NoSuchMethodException {

            Field pathListField = ShareReflectUtil.findField(loader, "pathList");
            Object dexPathList = pathListField.get(loader);

            ShareReflectUtil.expandFieldArray(dexPathList, "dexElements",
                    makeDexElements(dexPathList,
                            new ArrayList<File>(additionalClassPathEntries), optimizedDirectory));
        }

        private static Object[] makeDexElements(
                Object dexPathList, ArrayList<File> files, File optimizedDirectory)
                throws IllegalAccessException, InvocationTargetException,
                NoSuchMethodException {
            Method makeDexElements =
                    ShareReflectUtil.findMethod(dexPathList, "makeDexElements", ArrayList.class,
                            File.class);
            return (Object[]) makeDexElements.invoke(dexPathList, files, optimizedDirectory);
        }
    }
}
