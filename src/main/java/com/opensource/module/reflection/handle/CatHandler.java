package com.opensource.module.reflection.handle;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/14 10:04
 * @Version V1.0
 */
public class CatHandler {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        Properties properties = PropertiesHandler.buildProperties("reflection/cat.properties");
        String className = properties.getProperty("className");
        //we can change the 'reflection/cat.properties' to call different method.
        String methodName = properties.getProperty("methodName");
        Class clazz = Class.forName(className);
        Object object = clazz.newInstance();
        //Only public class method.
        Method method = clazz.getMethod(methodName);
        method.invoke(object);
        //Only public class field.
        String fieldName = properties.getProperty("fieldName");
        Field field = clazz.getField(fieldName);
        Object fieldValue = field.get(object);
        System.out.println(fieldValue);
        //Only public class constructor.
        Constructor constructor1 = clazz.getConstructor();
        System.out.println(constructor1);
        Constructor constructor2 = clazz.getConstructor(String.class);
        System.out.println(constructor2);
    }
}
