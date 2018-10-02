package com.ecjtu.fileuploaddemo.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8800);
            Socket socket = null;
            System.out.println("服务器即将启动监听...等待客户端连接");
            while (true){
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
