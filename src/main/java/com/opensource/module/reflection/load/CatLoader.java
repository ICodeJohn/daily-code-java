package com.opensource.module.reflection.load;

import com.opensource.module.reflection.entity.Car;
import com.opensource.module.reflection.entity.Cat;
import com.opensource.module.reflection.handle.CatHandler;
import com.sun.security.jgss.GSSUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLOutput;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/14 13:19
 * @Version V1.0
 */
public class CatLoader {

    private static final String CLASS_NAME = "com.opensource.module.reflection.entity.Car";

    /**
     * 右键可以查看类图
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        //哪个类的Class对象
        Class<?> clazz = Class.forName(CLASS_NAME);
        System.out.println(clazz);
        Car car = new Car();
        System.out.println(car.getClass());

        //运行类的class对象
        Class<? extends Class> real = clazz.getClass();
        System.out.println(real);
        System.out.println(car.getClass().getClass());

        //获取类的包名
        System.out.println(clazz.getPackage().getName());
        System.out.println(car.getClass().getPackage().getName());

        //获取类名
        System.out.println(clazz.getName());
        System.out.println(car.getClass().getName());

        //获取实例
        Car car1  = (Car) clazz.newInstance();
        System.out.println(car1);

        //获取字段
        Field brand = clazz.getField("brand");
        System.out.println(brand.get(car1));

        //设置字段属性
        brand.set(car1,"宝马");
        System.out.println(brand.get(car1));

        //获取全部字段
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.get(car1));
        }


    }
}
