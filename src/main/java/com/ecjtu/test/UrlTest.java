package com.ecjtu.test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 使用Url类获取百度首页
 */
public class UrlTest {
    public static void main(String[] args) throws IOException {
        try {
            // 创建URL对象实例
            URL baidu = new URL("https://www.baidu.com");
            // 打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream
            InputStream is = baidu.openStream();
            // 将字节流转换为字符流
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            // 为字符流添加缓存
            BufferedReader bufferedReader = new BufferedReader(isr);
            String data = bufferedReader.readLine();
            while(data!=null)
            {
                System.out.println(data);
                data = bufferedReader.readLine();
            }
            bufferedReader.close();
            isr.close();
            is.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
