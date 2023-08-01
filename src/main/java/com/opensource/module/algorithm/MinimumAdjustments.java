package com.opensource.module.algorithm;

import java.util.*;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/31 22:59
 * @Version V1.0
 */
public class MinimumAdjustments {

    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (in.hasNextLine()) {
            list.add(in.nextLine());
        }
        int n = Integer.parseInt(list.get(0));
        Deque<Integer> deque = new LinkedList<>();
        int start = 1;
        int count = 0;//这个是移动次数
        for (int j = 1; j < list.size(); j++) {
            String str = list.get(j);
            //如果是移除指令
            if (str.equals("remove")) {
                if (!deque.isEmpty()) {

                    if (deque.peekFirst() == start) {
                        //如果队列不为空，且移除来的就是start那么可以移除
                        deque.removeFirst();
                        start++;
                    } else {
                        //此时就需要调整顺序了
                        List<Integer> list1 = new ArrayList<>();
                        while (!deque.isEmpty()) {
                            list1.add(deque.removeFirst());
                        }
                        Collections.sort(list1);
                        for (int temp = 0; temp < list1.size(); temp++) {
                            deque.addLast(list1.get(temp));
                        }
                        deque.removeFirst();
                        count++;
                        start++;
                    }
                }
                continue;
            }
            String[] strs = str.split(" ");
            //如果是添加指令
            if (strs[1].equals("add")) {
                if (strs[0].equals("head")) {
                    //从头部添加
                    deque.addFirst(Integer.parseInt(strs[2]));
                } else {
                    //从尾部添加
                    deque.addLast(Integer.parseInt(strs[2]));
                }
            }
            System.out.println(deque);
        }
        System.out.println(count);
    }
}
