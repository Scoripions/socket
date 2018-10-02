package com.ecjtu.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的服务端
 */
public class Server {
    public static void main(String[] args) {


        try {
            // 1.创建服务器端ServerSocket的实例
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;

            // 自定义统计连接数
            int Count = 0;
            while(true) {
                // 2.创建监听
                System.out.println("服务端开启监听...准备收消息了");
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                Count++;
                System.out.println("当前的连接数为:"+Count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
