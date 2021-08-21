package com.brett.hotfixdemo.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author dell
 */
public class ShareReflectUtil {

    //从instance 到其父类 找name属性
    public static Field findField(Object instance, String name) throws NoSuchFieldException {
        for (Class<?> clazz = instance.getClass();clazz!=null;clazz = clazz.getSuperclass()){
            try {
                //查找当前类的属性 (不包含父类)
                Field field = clazz.getDeclaredField(name);
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        throw new NoSuchFieldException("Field "+name+" not found in "+instance.getClass());
    }

    //从instance 到其父类 找name方法
    public static Method findMethod(Object instance, String name,Class<?>... parameterTypes) throws NoSuchMethodException {
        for (Class<?> clazz = instance.getClass();clazz!=null;clazz = clazz.getSuperclass()){
            try {
                Method method = clazz.getDeclaredMethod(name,parameterTypes);
                if(!method.isAccessible()){
                    method.setAccessible(true);
                }
                return method;
            } catch ( NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        throw new NoSuchMethodException("Method "+name+" with parameters "+ Arrays.asList(parameterTypes) +" not found in "+instance.getClass());
    }

    public static void expandFieldArray(Object instance, String fieldName, Object[] patchElements) throws NoSuchFieldException, IllegalAccessException {
        //拿到classloader中的dexelements数组
        Field dexElementsField = findField(instance,fieldName);
        //old Element[]
        Object[] dexElements = (Object[]) dexElementsField.get(instance);
        //合并后的数组
        Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(),dexElements.length+patchElements.length);
        //先拷贝新数组
        System.arraycopy(patchElements,0,newElements,0,patchElements.length);
        System.arraycopy(dexElements,0,newElements,patchElements.length,dexElements.length);
        //修改classLoader中pathList的 dexelements
        dexElementsField.set(instance,newElements);

    }
}
