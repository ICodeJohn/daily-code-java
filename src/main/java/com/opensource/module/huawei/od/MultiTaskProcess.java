package com.opensource.module.huawei.od;

import java.util.*;

public class MultiTaskProcess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取任务数据
        String[] taskStrs = scanner.nextLine().split(" ");
        int n = taskStrs.length / 2;
        int[][] tasks = new int[n][2];
        for (int i = 0; i < n; i++) {
            tasks[i][0] = Integer.parseInt(taskStrs[2 * i]);     // submitTime
            tasks[i][1] = Integer.parseInt(taskStrs[2 * i + 1]); // costTime
        }

        // 读取队列长度和执行者数量
        int maxQueueLen = scanner.nextInt();
        int workerCount = scanner.nextInt();

        // 执行者空闲时间（0表示空闲，>0表示忙碌到该时刻）
        int[] workerFreeTime = new int[workerCount + 1]; // 1-based index
        Queue<int[]> taskQueue = new LinkedList<>();
        int discardCount = 0;
        int taskIndex = 0;
        int lastFinishTime = 0;

        // 优先队列：忙碌的执行者 (endTime, workerId)
        PriorityQueue<int[]> busyHeap = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        // 时间推进模拟
        int currentTime = 0;
        while (taskIndex < n || !busyHeap.isEmpty() || !taskQueue.isEmpty()) {
            // 1. 处理当前时刻完成的工作
            while (!busyHeap.isEmpty() && busyHeap.peek()[0] == currentTime) {
                int[] finished = busyHeap.poll();
                int workerId = finished[1];
                workerFreeTime[workerId] = 0; // 标记为空闲
            }

            // 2. 让所有空闲执行者取任务执行
            for (int wid = 1; wid <= workerCount; wid++) {
                if (workerFreeTime[wid] == 0 && !taskQueue.isEmpty()) {
                    int[] task = taskQueue.poll();
                    int endTime = currentTime + task[1];
                    workerFreeTime[wid] = endTime;
                    busyHeap.offer(new int[]{endTime, wid});
                    lastFinishTime = Math.max(lastFinishTime, endTime);
                }
            }

            // 3. 处理当前时刻提交的任务
            while (taskIndex < n && tasks[taskIndex][0] == currentTime) {
                if (taskQueue.size() == maxQueueLen) {
                    // 队列满，丢弃最老任务
                    taskQueue.poll();
                    discardCount++;
                }
                taskQueue.offer(tasks[taskIndex]);
                taskIndex++;
            }

            // 4. 时间推进到下一个事件点
            int nextTime = Integer.MAX_VALUE;
            if (taskIndex < n) {
                nextTime = Math.min(nextTime, tasks[taskIndex][0]);
            }
            if (!busyHeap.isEmpty()) {
                nextTime = Math.min(nextTime, busyHeap.peek()[0]);
            }
            if (nextTime == Integer.MAX_VALUE) break;
            currentTime = nextTime;
        }

        System.out.println(lastFinishTime + " " + discardCount);
        scanner.close();
    }
}
