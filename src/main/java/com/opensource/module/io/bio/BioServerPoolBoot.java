package com.opensource.module.io.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/12 09:38
 * @Version V1.0
 */
public class BioServerPoolBoot {

    public static void main(String[] args) {
        int port = 80;
        ThreadPoolExecutor socketPool = null;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            int cpuNum = Runtime.getRuntime().availableProcessors();
            socketPool = new ThreadPoolExecutor(
                    cpuNum,
                    cpuNum * 2,
                    1000,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(200),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.DiscardOldestPolicy()
            );
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                socketPool.submit(new BioServerHandle(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socketPool.shutdown();
        }
    }
}
