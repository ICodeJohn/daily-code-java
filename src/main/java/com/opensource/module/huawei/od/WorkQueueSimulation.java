package com.opensource.module.huawei.od;
import java.util.*;

public class WorkQueueSimulation {

    static class Task {
        int submitTime;
        int duration;
        Task(int s, int d) {
            submitTime = s;
            duration = d;
        }
    }

    static class Worker {
        int id;
        int finishTime; // 当前任务完成时间
        Worker(int id) {
            this.id = id;
            this.finishTime = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取任务
        List<Task> tasks = new ArrayList<>();
        String[] arr = sc.nextLine().split(" ");
        for (int i = 0; i < arr.length; i += 2) {
            int t = Integer.parseInt(arr[i]);
            int d = Integer.parseInt(arr[i + 1]);
            tasks.add(new Task(t, d));
        }

        int maxQueue = sc.nextInt();
        int workerCount = sc.nextInt();

        // 等待队列
        Deque<Task> queue = new ArrayDeque<>();

        // 空闲执行者（按编号）
        PriorityQueue<Worker> idle = new PriorityQueue<>();

        // 忙碌执行者（按完成时间）
        PriorityQueue<Worker> busy = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.finishTime)
        );

        for (int i = 1; i <= workerCount; i++) {
            idle.offer(new Worker(i));
        }

        int dropCount = 0;
        int currentTime = 0;

        for (Task task : tasks) {
            currentTime = task.submitTime;

            // 处理在当前提交时间之前或等于的完成事件
            while (!busy.isEmpty() && busy.peek().finishTime <= currentTime) {
                Worker w = busy.poll();
                if (!queue.isEmpty()) {
                    Task t = queue.poll();
                    w.finishTime = Math.max(w.finishTime, t.submitTime) + t.duration;
                    busy.offer(w);
                } else {
                    idle.offer(w);
                }
            }

            // 提交当前任务
            if (!idle.isEmpty()) {
                Worker w = idle.poll();
                w.finishTime = currentTime + task.duration;
                busy.offer(w);
            } else {
                if (queue.size() == maxQueue) {
                    queue.poll(); // 丢弃最老任务
                    dropCount++;
                }
                queue.offer(task);
            }
        }

        // 处理剩余任务
        while (!busy.isEmpty()) {
            Worker w = busy.poll();
            currentTime = w.finishTime;
            if (!queue.isEmpty()) {
                Task t = queue.poll();
                w.finishTime = currentTime + t.duration;
                busy.offer(w);
            }
        }

        System.out.println(currentTime + " " + dropCount);
    }
}
