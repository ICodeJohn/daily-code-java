package com.opensource.module.huawei.od;

import java.util.*;

public class CutInline {
    // 定义客户类
    static class Customer {
        int id;
        int priority;
        int time; // 到达顺序，用于稳定性

        Customer(int id, int priority, int time) {
            this.id = id;
            this.priority = priority;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // 读掉换行符

        // 自增时间戳
        int timestamp = 0;

        // 优先队列：优先级升序？1 最高，数字越小优先级越高
        PriorityQueue<Customer> pq = new PriorityQueue<>(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1.priority != o2.priority) {
                    return o1.priority - o2.priority; // 优先级小的排前面（1 最高）
                } else {
                    return o1.time - o2.time; // 时间小的先来
                }
            }
        });

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            if (parts[0].equals("a")) {
                int id = Integer.parseInt(parts[1]);
                int pri = Integer.parseInt(parts[2]);
                pq.offer(new Customer(id, pri, timestamp++));
            } else if (parts[0].equals("p")) {
                if (!pq.isEmpty()) {
                    Customer c = pq.poll();
                    System.out.println(c.id);
                }
            }
        }
    }
}
