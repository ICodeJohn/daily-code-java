package com.opensource.module.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/12 09:16
 * @Version V1.0
 */
public class BioServerBoot {

    public static void main(String[] args) {
        int port = 80;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();
            new Thread(new BioServerHandle(socket)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
