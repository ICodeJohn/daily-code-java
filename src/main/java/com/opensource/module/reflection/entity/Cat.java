package com.opensource.module.reflection.entity;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/14 09:49
 * @Version V1.0
 */
public class Cat {

    private String name = "Tom";
    public int age = 5;

    public Cat() {
        System.out.println("11111111");
    }

    public Cat(String name) {
        this.name = name;
    }

    {
        System.out.println("-----------");
    }

    public void sayHi() {
        System.out.println(String.format("Hi,I'm %s.", name));
    }

    public void sayBye() {
        System.out.println("Bye, See you next.");
    }

    public void execute() {

    }
}
