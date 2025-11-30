package com.opensource.module.algorithm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadStateDemo {

    private static final Object lock = new Object();
    private static final Lock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Java线程状态演示 ===\n");

        // 1. 新建状态 (NEW)
        //demonstrateNewState();

        // 2. 就绪和运行状态 (RUNNABLE)
        //demonstrateRunnableState();

        // 3. 阻塞状态 - synchronized (BLOCKED)
        //demonstrateBlockedState();

        // 4. 等待状态 (WAITING)
        demonstrateWaitingState();

        // 5. 超时等待状态 (TIMED_WAITING)
        //demonstrateTimedWaitingState();

        // 6. 终止状态 (TERMINATED)
        //demonstrateTerminatedState();
    }

    /**
     * 1. 演示新建状态 (NEW)
     * 线程被创建但尚未调用start()方法
     */
    private static void demonstrateNewState() {
        System.out.println("1. 新建状态 (NEW):");
        Thread thread = new Thread(() -> {
            System.out.println("   新线程开始执行");
        });
        System.out.println("   线程状态: " + thread.getState());
        System.out.println("   说明: 线程已创建，但尚未启动\n");
    }

    /**
     * 2. 演示就绪和运行状态 (RUNNABLE)
     * 调用start()后进入就绪状态，获得CPU时间片后进入运行状态
     */
    private static void demonstrateRunnableState() throws InterruptedException {
        System.out.println("2. 就绪和运行状态 (RUNNABLE):");

        Thread thread = new Thread(() -> {
            System.out.println("   线程正在运行中...");
            // 模拟一些工作
            for (int i = 0; i < 3; i++) {
                System.out.println("   执行任务 " + (i + 1));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        // 短暂等待让线程进入运行状态
        //Thread.sleep(50);
        System.out.println("   线程状态: " + thread.getState());

        thread.join(); // 等待线程结束
        System.out.println();
    }

    /**
     * 3. 演示阻塞状态 (BLOCKED)
     * 线程等待获取synchronized锁时进入阻塞状态
     */
    private static void demonstrateBlockedState() throws InterruptedException {
        System.out.println("3. 阻塞状态 (BLOCKED):");

        // 第一个线程获取锁
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("   线程1获取到锁，将持有3秒钟");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 第二个线程尝试获取同一个锁
        Thread thread2 = new Thread(() -> {
            System.out.println("   线程2尝试获取锁...");
            synchronized (lock) {
                System.out.println("   线程2成功获取到锁");
            }
        });

        thread1.start();
        Thread.sleep(100); // 确保thread1先启动

        thread2.start();
        Thread.sleep(100); // 确保thread2开始执行

        // 此时thread2应该处于BLOCKED状态
        System.out.println("   线程2状态: " + thread2.getState());
        System.out.println("   说明: 线程2正在等待线程1释放锁\n");

        thread1.join();
        thread2.join();

    }

    /**
     * 4. 演示等待状态 (WAITING)
     * 线程调用Object.wait()或Thread.join()时进入等待状态
     */
    private static void demonstrateWaitingState() throws InterruptedException {
        System.out.println("4. 等待状态 (WAITING):");

        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {
            try {
                System.out.println("   工作线程开始执行");
                Thread.sleep(1000);
                System.out.println("   工作线程执行完毕，通知主线程继续");
                // 唤醒等待的主线程
                synchronized (mainThread) {
                    mainThread.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        // 主线程等待工作线程完成
        synchronized (mainThread) {
            System.out.println("   主线程开始等待...");
            long startTime = System.currentTimeMillis();
            mainThread.wait(5000); // 最多等待5秒
            long endTime = System.currentTimeMillis();
            System.out.println("   主线程结束等待，等待时间: " + (endTime - startTime) + "ms");
        }

        // 检查工作线程状态
        Thread.sleep(100);
        System.out.println("   工作线程状态: " + thread.getState());
        System.out.println();
    }

    /**
     * 5. 演示超时等待状态 (TIMED_WAITING)
     * 线程调用Thread.sleep()或Object.wait(timeout)时进入超时等待状态
     */
    private static void demonstrateTimedWaitingState() throws InterruptedException {
        System.out.println("5. 超时等待状态 (TIMED_WAITING):");

        Thread thread = new Thread(() -> {
            try {
                System.out.println("   线程开始睡眠2秒钟");
                Thread.sleep(2000);
                System.out.println("   线程唤醒并继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        Thread.sleep(100); // 确保线程进入睡眠

        System.out.println("   线程状态: " + thread.getState());
        System.out.println("   说明: 线程正在睡眠，有明确的超时时间\n");

        thread.join();
    }

    /**
     * 6. 演示终止状态 (TERMINATED)
     * 线程执行完毕或异常结束时进入终止状态
     */
    private static void demonstrateTerminatedState() throws InterruptedException {
        System.out.println("6. 终止状态 (TERMINATED):");

        Thread thread = new Thread(() -> {
            System.out.println("   线程执行简单任务");
            System.out.println("   线程即将结束");
        });

        System.out.println("   启动前状态: " + thread.getState());
        thread.start();
        thread.join(); // 等待线程结束

        System.out.println("   结束后状态: " + thread.getState());
        System.out.println("   说明: 线程已执行完毕，生命周期结束");
    }
}