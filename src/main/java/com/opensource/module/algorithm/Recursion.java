package com.opensource.module.algorithm;

import java.io.IOException;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/8/3 12:41
 * @Version V1.0
 */
public class Recursion {


    public static void main(String[] args) {
        //创建一个本身方法。
        Recursion instance = new Recursion();
        byte[] b = new byte[32];
        //定义一个死循环。
        while (true) {
            try {
                System.out.println("请输入正整数,获取其阶乘，输入over结束");
                System.in.read(b);//控制台输入数据
                String res = new String(b).trim();//将控制台输入的数据转为String并去除两端空格
                if (res.equals("over")) {
                    System.out.println("程序结束");
                    break;
                }
                int i = Integer.parseInt(res.trim());//将控制台输入的转换为int类型
                if (i <= 0) {
                    continue;
                }
                //调用factorial方法给递归次数赋初始值0。并开始调用递归方法
                System.out.println(i + "!=" + instance.factorial(i));
                //获取递归次数。
                System.out.println("递归了 " + instance.getCount() + " 次");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException ne) {
                System.out.println("请输入正整数");
            }
        }
    }

    // 存递归次数的变量
    private int count = 0;

    //将递归次数返回给调用者
    public int getCount() {
        return count;
    }

    public long factorial(int i) {
// 将递归次数清零,若要统计所有的，则将count注释即可
        count = 0;
        //调用递归方法，并将递归次数结果返回。
        return this.getResult(i);
    }

    //递归方法。计算递归次数。
    public long getResult(int i) {
        count++;
        return i == 1 ? i : i * getResult(i - 1);
    }
}
