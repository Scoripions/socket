package com.ecjtu.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

/**
 * 基于UDP的客户端
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1.定义要发送的信息
        String info = new Date()+"\t"+Math.random();
        // 2.创建DatagramPacket,包含要发送的消息
        DatagramPacket packet = new DatagramPacket(info.getBytes(),info.getBytes().length,InetAddress.getByName("localhost"),8866);
        // 3.创建DatagramSocket
        DatagramSocket socket = new DatagramSocket();
        // 4.发送数据
        socket.send(packet);

        /**
         * 接收服务器端响应的数据
         */
        byte[] fromServerMessage = new byte[1024];
        DatagramPacket fromServer = new DatagramPacket(fromServerMessage,fromServerMessage.length);
        socket.receive(fromServer);
        // 读取数据信息
        String fromServerStr = new String(fromServerMessage,0,fromServerMessage.length);
        System.out.println("服务器端的反馈数据为:" + fromServerStr);


    }
}
