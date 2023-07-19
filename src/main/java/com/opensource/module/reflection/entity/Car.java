package com.opensource.module.reflection.entity;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/14 15:05
 * @Version V1.0
 */
public class Car {

    public String color="白色";
    public int price = 100000;
    public String brand = "奔驰";

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
