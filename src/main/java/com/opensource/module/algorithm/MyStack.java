package com.opensource.module.algorithm;

import java.util.Arrays;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/22 17:19
 * @Version V1.0
 */
public class MyStack<E> {

    //声明一个数组来存储数据
    private Object stack[] = null;

    //声明栈顶
    private int top = -1;

    //记录栈的容量
    private int size;

    //构造方法初始化
    public MyStack(int size) {
        this.size = size;
        this.stack = new Object[size];
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    public boolean isFull() {
        return top == size - 1;
    }


    //入栈
    public void push(E value) throws RuntimeException {

        //判断 空间是否已满
        if (isFull()) {
            //扩容
            stack = Arrays.copyOf(stack, size * 2);
        }

        //若栈未满则压栈
        stack[++top] = value;
    }

    //出栈（只能从栈顶一个一个的出栈）
    public E pop() throws RuntimeException {

        //判断是否栈空
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        //栈不为空则弹栈
        Object object = stack[top--];
        return (E) object;
    }


    @Override
    public String toString() {
        return Arrays.toString(stack);
    }
}
