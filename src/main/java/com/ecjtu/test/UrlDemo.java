package com.ecjtu.test;

import java.net.URL;

/**
 * Url类中常用API
 */
public class UrlDemo {
    public static void main(String[] args) throws Exception {
        URL baidu = new URL("https://www.baidu.com");
        URL content = new URL(baidu,"/index.html?username=Scoripion#test");
        System.out.println("协议为:"+content.getProtocol());
        System.out.println("默认的端口为:"+content.getDefaultPort());
        // 这里返回的不是80端口 因为在创建的时候未指定相应的端口号
        System.out.println("此URL的端口号"+content.getPort());
        System.out.println("此URL的路径部分"+content.getPath());
        System.out.println("URL的内容为:"+content.getContent());
        System.out.println("查询的内容为:"+content.getQuery());
        System.out.println("相对路径为:"+content.getRef());
        System.out.println("文件名:"+content.getFile());
    }
}
