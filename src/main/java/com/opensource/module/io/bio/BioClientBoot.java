package com.opensource.module.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/12 09:17
 * @Version V1.0
 */
public class BioClientBoot {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 80;
        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            String message;
            do {
                System.out.println("请输入:");
                Scanner input = new Scanner(System.in);
                message = input.nextLine();
                if(message == null || message.length() == 0){
                    System.out.println("Socket结束请求");
                    break;
                }
                writer.println(message);
                String answer = reader.readLine();
                System.out.println(answer);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
