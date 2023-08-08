package com.opensource.module.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/8/3 17:39
 * @Version V1.0
 */
public class MutilArrayMerge {

    private static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 5, 8, 10);
        List<Integer> list2 = Arrays.asList(3, 6, 9);
        List<Integer> list3 = Arrays.asList(4, 7, 17, 18);
        List<List<Integer>> check = new ArrayList<>();
        check.add(list1);
        check.add(list2);
        check.add(list3);
        merge(check);
        System.out.println(result.toString());
    }


    public static void merge(List<List<Integer>> nums) {

        PriorityQueue<Item> queue = new PriorityQueue();

        for (int i = 0; i < nums.size(); i++) {
            Item item = new Item(nums.get(i).get(0));
            item.row = i;
            item.col = 0;
            queue.offer(item);
        }
        dfs(queue, nums);
    }

    public static void dfs(PriorityQueue<Item> queue, List<List<Integer>> nums) {
        if (queue.peek().end) {
            return;
        }
        Item item = queue.poll();
        result.add(item.value);
        int row = item.row;
        int col = item.col + 1;
        if (col >= nums.get(row).size()) {
            item.end = Boolean.TRUE;
            item.value = Integer.MAX_VALUE;
        } else {
            item.value = nums.get(row).get(col);
            item.col = col;
        }
        queue.offer(item);
        dfs(queue, nums);
    }


    public static class Item implements Comparable<Item> {
        int row;
        int col;
        int value;
        boolean end;

        public Item(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
