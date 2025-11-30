package com.opensource.module.huawei.od;

import java.util.*;

public class BasketSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] putStr = scanner.nextLine().split(",");
        String[] expectStr = scanner.nextLine().split(",");

        Queue<Integer> putQueue = new LinkedList<>();
        for (String s : putStr) putQueue.offer(Integer.parseInt(s));

        int[] expected = new int[expectStr.length];
        for (int i = 0; i < expectStr.length; i++) expected[i] = Integer.parseInt(expectStr[i]);

        Deque<Integer> deque = new LinkedList<>();
        StringBuilder ans = new StringBuilder();

        for (int target : expected) {
            // 放入桶直到桶的两边出现目标
            while (!deque.contains(target) && !putQueue.isEmpty()) {
                deque.offerLast(putQueue.poll());
            }

            if (deque.isEmpty() || (!deque.contains(target))) {
                System.out.println("NO");
                return;
            }

            if (deque.size() == 1) {
                if (deque.peekFirst() != target) {
                    System.out.println("NO");
                    return;
                }
                deque.pollFirst();
                ans.append("L");
            } else {
                if (deque.peekFirst() == target) {
                    deque.pollFirst();
                    ans.append("L");
                } else if (deque.peekLast() == target) {
                    deque.pollLast();
                    ans.append("R");
                } else {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println(ans.toString());
    }
}
