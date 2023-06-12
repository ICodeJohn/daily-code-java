package com.opensource.module.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 11.05-11.15 n001 n101 n102,n103  --001025
 *
 * @Title: "Java bio handle runnable"
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/12 09:12
 * @Version V1.0
 */
public class BioServerHandle implements Runnable {
    private Socket socket;

    public BioServerHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            String message;
            do {
                message = reader.readLine();
                if (message == null || message.length() == 0) {
                    break;
                }
                System.out.println("收到客户端消息：" + message + "");
                writer.println("接收成功");
            } while (true);
            System.out.println("Socket 断开链接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
