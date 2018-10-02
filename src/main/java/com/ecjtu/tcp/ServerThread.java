package com.ecjtu.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 多线程情况下的服务端
 */
public class ServerThread extends Thread {
    private Socket socket;
    public ServerThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 3.创建对应的输入流 读取客户端的信息
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            bufferedReader = new BufferedReader(isr);
            String data = null;
            while((data = bufferedReader.readLine())!=null)
            {
                System.out.println("客户端的消息为:"+data);
            }
            // 4.关闭输入流
            socket.shutdownInput();
            // 5.获取输出流
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("Welcome..");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                // 关闭流 这里还要判断非空
                pw.close();
                os.close();
                bufferedReader.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
