package com.opensource.module.huawei.od;
import java.util.*;

public class WorkQueueSimulation {

    static class Task {
        int submitTime;
        int duration;

        Task(int t, int d) {
            this.submitTime = t;
            this.duration = d;
        }
    }

    static class WorkerState {
        int id;         // 执行者编号
        int finishTime; // 当前任务的完成时刻

        WorkerState(int id, int finish) {
            this.id = id;
            this.finishTime = finish;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ------- 读取任务 -------
        String[] arr = sc.nextLine().trim().split(" ");
        int N = arr.length / 2;
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 2) {
            int t = Integer.parseInt(arr[i]);
            int d = Integer.parseInt(arr[i + 1]);
            tasks.add(new Task(t, d));
        }

        // ------- 读取队列长度 & 执行者数量 -------
        int queueMax = sc.nextInt();
        int workerCount = sc.nextInt();

        // ------- FIFO 工作队列 -------
        Deque<Task> workQueue = new ArrayDeque<>();

        // ------- 执行中任务的最小堆（按完成时间升序，若同则按执行者编号升序） -------
        PriorityQueue<WorkerState> working =
                new PriorityQueue<>((a, b) ->
                        a.finishTime == b.finishTime ? a.id - b.id : a.finishTime - b.finishTime);

        // ------- 空闲执行者最小堆（按编号升序） -------
        PriorityQueue<Integer> idleWorkers = new PriorityQueue<>();
        for (int i = 1; i <= workerCount; i++) {
            idleWorkers.offer(i);
        }

        int dropCount = 0;      // 被丢弃任务数量
        int lastFinishTime = 0; // 所有任务的最终完成时刻

        // ============================================================
        // 主循环：处理每个任务提交事件
        // ============================================================
        for (Task task : tasks) {
            int now = task.submitTime;

            // 1. 先处理：所有完成时间 <= now 的执行者
            while (!working.isEmpty() && working.peek().finishTime <= now) {
                WorkerState ws = working.poll();
                idleWorkers.offer(ws.id);

                // 执行者空闲 → 立即从队列取最老任务执行
                if (!workQueue.isEmpty()) {
                    Task qTask = workQueue.pollFirst();
                    int finish = ws.finishTime + qTask.duration;
                    lastFinishTime = Math.max(lastFinishTime, finish);
                    working.offer(new WorkerState(ws.id, finish));
                    idleWorkers.remove(ws.id); // 他不空闲了
                }
            }

            // 2. 当前事件：提交新任务
            if (workQueue.size() < queueMax) {
                // 队列未满 → 正常入队
                workQueue.offerLast(task);
            } else {
                // 队列已满
                // (注意：如果刚才执行者空闲事件已经取走队头，队列可能在 step1 中变短)
                if (workQueue.size() < queueMax) {
                    // 因为空闲事件中取走了任务，这里又可以入队了
                    workQueue.offerLast(task);
                } else {
                    // 队列仍满 → 丢弃最老任务
                    workQueue.pollFirst();
                    dropCount++;
                    workQueue.offerLast(task);
                }
            }

            // 3. 再尝试分配任务：如果执行者空闲 + 队列非空 → 派任务执行
            while (!idleWorkers.isEmpty() && !workQueue.isEmpty()) {
                int wid = idleWorkers.poll();
                Task qTask = workQueue.pollFirst();

                int finish = now + qTask.duration;
                lastFinishTime = Math.max(lastFinishTime, finish);

                working.offer(new WorkerState(wid, finish));
            }
        }

        // ============================================================
        // 所有任务提交完毕后，还要处理剩余执行中的任务
        // ============================================================
        while (!working.isEmpty()) {
            WorkerState ws = working.poll();
            lastFinishTime = Math.max(lastFinishTime, ws.finishTime);

            idleWorkers.offer(ws.id);
            if (!workQueue.isEmpty()) {
                Task qTask = workQueue.pollFirst();

                int finish = ws.finishTime + qTask.duration;
                lastFinishTime = Math.max(lastFinishTime, finish);

                working.offer(new WorkerState(ws.id, finish));
                idleWorkers.remove(ws.id); // 再次占用
            }
        }

        // 输出结果
        System.out.println(lastFinishTime + " " + dropCount);
    }
}
