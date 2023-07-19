package com.opensource.module.reflection.handle;

import com.opensource.module.reflection.entity.Cat;

import java.lang.reflect.Method;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/14 11:42
 * @Version V1.0
 */
public class CatExehandler {

    private static final String CLASS_NAME = "com.opensource.module.reflection.entity.Cat";
    private static final String METHOD_NAME_SAYHI = "sayHi";
    private static final String METHOD_NAME_EXE = "execute";

    /**
     * 1.在下述例子中如果同时用普通和反射两种方式调用sayHi，反射更快一些，这是因为代码中有相似结构，反射调用时已经有代码预热导致错觉。
     * 2.如果在排除代码块自身的问题后，反射比和普通调用慢很多。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        //调用普通方法
        long exeCommonSayHi = exeCommonSayHi();
        long exeReflectionSayHi = exeReflection(METHOD_NAME_SAYHI);
        System.out.println(String.format("sayHiCommon cost: %d",exeCommonSayHi));
        System.out.println(String.format("sayHiReflection cost: %d",exeReflectionSayHi));

        //调用空方法排除其他模块干扰
        long exeCommonExe = exeCommonExecute();
        long exeReflectionExe = exeReflection(METHOD_NAME_EXE);
        //关闭反射访问检查，提升反射效率
        long exeReflectionAccess = exeReflectionAccess(METHOD_NAME_EXE);
        System.out.println(String.format("exeCommon cost: %d",exeCommonExe));
        System.out.println(String.format("exeReflection cost: %d",exeReflectionExe));
        System.out.println(String.format("exeReflectionAccess cost: %d",exeReflectionAccess));

    }

    public static long exeCommonSayHi() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            cat.sayHi();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long exeCommonExecute() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            cat.execute();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }


    public static long exeReflection(String methodName) throws Exception {
        Class clazz = Class.forName(CLASS_NAME);
        Object object = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(methodName);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            method.invoke(object);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }


    public static long exeReflectionAccess(String methodName) throws Exception {
        Class clazz = Class.forName(CLASS_NAME);
        Object object = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(methodName);
        method.setAccessible(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            method.invoke(object);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
