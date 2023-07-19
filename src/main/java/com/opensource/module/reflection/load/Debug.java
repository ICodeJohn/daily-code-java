package com.opensource.module.reflection.load;

import com.opensource.module.reflection.entity.Cat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/14 17:45
 * @Version V1.0
 */
public class Debug {

    //TODO  force step into is not enable.
    public static void main(String[] args) {
        //代码块
        Cat cat =new Cat();
        //静态内部类
        Inner1 inner1 = new Inner1();
        Inner1 inner2 = new Inner1();
        System.out.println(inner1.hashCode() == inner2.hashCode());

        //成员内部类
        Inner2 innerBata = new Debug().new Inner2();

        //局部内部类
        class Inner3 {

        }
        Inner3 inner3 = new Inner3();


        Pattern pattern = Pattern.compile("(\\d\\d\\d)?");
        Matcher matcher = pattern.matcher("123456789");
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }

        String str1 = "[^a-z]{2}";
        Pattern pattern1 =Pattern.compile(str1);
        Matcher matcher1 = pattern1.matcher("123456789");
        while (matcher1.find()){
            System.out.println(matcher1.group(0));
        }

        String str2 = "\\s";
        Pattern pattern2 =Pattern.compile(str2);
        Matcher matcher2 = pattern2.matcher("123\nabc");
        while (matcher2.find()){
            System.out.println("a"+matcher2.group(0)+"b");
        }
    }

    public static class Inner1{

    }

    public  class Inner2{

    }
}
