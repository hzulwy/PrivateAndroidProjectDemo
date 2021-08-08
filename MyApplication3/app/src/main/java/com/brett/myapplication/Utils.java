package com.brett.myapplication;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Utils {

    public static void injectEvent(Activity activity){
        if(null == activity){
            return;
        }

        Class<? extends Activity> activityClass = activity.getClass();
        Method[] declaredMethods = activityClass.getDeclaredMethods();

        for (Method method : declaredMethods){
            if(method.isAnnotationPresent(onClick.class)){

                onClick annotation = method.getAnnotation(onClick.class);
                //获取button id
                int[] value = annotation.value();
                //获取EventType
                EventType eventType = annotation.annotationType().getAnnotation(EventType.class);
                Class listenerType = eventType.listenerType();
                String listenerSetter = eventType.listenerSetter();
                String methodName = eventType.methodName();

                ProxyHandler proxyHandler = new ProxyHandler(activity);
                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),new Class[]{listenerType},proxyHandler);
                proxyHandler.mapMethod(methodName,method);

                for (int id :value){
                    try {
                        //找到Button
                        Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
                        findViewByIdMethod.setAccessible(true);
                        View btn = (View) findViewByIdMethod.invoke(activity, id);
                        //根据listenerSetter方法名和listenerType方法参数找到method
                        Method listenerSetMethod = btn.getClass().getMethod(listenerSetter, listenerType);
                        listenerSetMethod.setAccessible(true);
                        listenerSetMethod.invoke(btn, listener);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public static void injectView(Activity activity) {
        if (null == activity) {
            return;
        }

        Class<? extends Activity> activityClass = activity.getClass();
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                //找到InjectView注解的field
                InjectView annotation = field.getAnnotation(InjectView.class);
                //找到button的id
                int value = annotation.value();

                try {
                    //找到findViewById方法
                    Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
                    findViewByIdMethod.setAccessible(true);
                    View btn = (View)findViewByIdMethod.invoke(activity, value);
                    field.set(activity,btn);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


            }
        }

    }
}
