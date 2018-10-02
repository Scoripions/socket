package com.ecjtu.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * InetAddress中API的使用
 */
public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            // 获取InetAddress的实例 返回本地主机
            InetAddress host = InetAddress.getLocalHost();
            // InetAddress 对象的原始 IP 地址(数组的形式)
            System.out.println(Arrays.toString(host.getAddress()));
            // 获取此 IP 地址的主机名
            System.out.println(host.getHostName());
            // 返回 IP 地址字符串（以文本表现形式）
            System.out.println(host.getHostAddress());
            // 获取此 IP 地址的完全限定域名
            System.out.println(host.getCanonicalHostName());

            System.out.println("-----------------");

            // 通过主机名来获取实例
            // InetAddress host2 = InetAddress.getByName("DESKTOP-0VGS4NV");
            InetAddress host2 = InetAddress.getByName("192.168.13.1");
            System.out.println(Arrays.toString(host2.getAddress()));
            System.out.println(host2.getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
