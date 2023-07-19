package com.opensource.module.reflection.load;

import com.opensource.module.reflection.entity.Car;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/14 15:38
 * @Version V1.0
 */
public class ObjectLoader {

    private static final String CLASS_NAME = "com.opensource.module.reflection.entity.Cat";

    public static void main(String[] args) throws Exception {

        //1.根据配置文件加载
        Class<?> clazz1 = Class.forName(CLASS_NAME);
        Object object1 = clazz1.newInstance();

        //2.构造函数传参
        Class<Car> carClass = Car.class;
        Car object2 = carClass.getConstructor().newInstance();

        //3.对象.class
        Car car = new Car();
        Car object3 = car.getClass().newInstance();

        //4.通过类加载器得到对象

        ClassLoader classLoader = car.getClass().getClassLoader();
        Class<?> clazz2 = classLoader.loadClass(CLASS_NAME);


        //基本数据类型也可以获取到对应的类
        Class<Integer> integerClass = int.class;
        Class<Double> doubleClass = double.class;
        System.out.println(doubleClass);
        Class<Boolean> booleanClass = boolean.class;

        //基本类型包装类 可以通过 .TYPE获得
        Class<Integer> integerClass1 = Integer.TYPE;
        Class<Character> characterClass = Character.TYPE;

        System.out.println(integerClass.hashCode());
        System.out.println(integerClass1.hashCode());

        //特殊Class
        Class<double[][]> aClass = double[][].class;
        System.out.println(aClass);
        Class<Class> classClass = Class.class;


    }
}
