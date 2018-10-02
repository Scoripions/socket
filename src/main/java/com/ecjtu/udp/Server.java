package com.ecjtu.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 基于UDP的服务端
 */
public class Server {
    public static void main(String[] args) throws Exception  {
        // 1.创建DatagramSocket对象
        DatagramSocket datagramSocket = new DatagramSocket(8866);
        byte[] data = new byte[1024];
        // 2.创建DatagramPacket对象,用来接收客户端的信息
        DatagramPacket packet = new DatagramPacket(data,data.length,datagramSocket.getInetAddress(),8866);
        System.out.println("服务端开启监听...准备收消息了");
        datagramSocket.receive(packet);
        // 3.读取信息
        String info = new String(data,0,data.length);
        System.out.println("Message from Client :" + info);

        /**
         * 向客户端响应信息
         */
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] toClientMessage = "Welcome to this Server..".getBytes();
        DatagramPacket toClient = new DatagramPacket(toClientMessage,toClientMessage.length,address,port);
        datagramSocket.send(toClient);

        // 关闭资源
        datagramSocket.close();
    }
}
