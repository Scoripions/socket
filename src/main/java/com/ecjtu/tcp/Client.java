package com.ecjtu.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.UUID;

/**
 * 基于TCP的客户端
 */
public class Client {
    public static void main(String[] args) {
        try {
            // 1.创建客户端实例
            Socket socket = new Socket("localhost",8888);

            // 2.获取输出流
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("Time"+new Date()+"\tMessage:"+ UUID.randomUUID().toString());
            pw.flush();
            socket.shutdownOutput();

            // 3.获取反馈 输入流
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String data = null;
            while((data=bufferedReader.readLine())!=null)
            {
                System.out.println("服务端的反馈为:"+data);
            }
            // 关闭流
            bufferedReader.close();
            isr.close();
            is.close();
            pw.close();
            os.close();

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
