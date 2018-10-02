package com.ecjtu.fileuploaddemo.socket;

import com.ecjtu.fileuploaddemo.entity.File;
import com.ecjtu.fileuploaddemo.entity.User;
import com.ecjtu.fileuploaddemo.util.CommandTransfer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner input = new Scanner(System.in);
    /**
     * 客户端的Socket
     */
    private Socket socket = null;

    public void showMainMenu() {
        System.out.println("欢迎使用ECJTU文件上传");
        System.out.println("1.登录\n2.注册\n3.退出");
        System.out.println("*************");
        System.out.println("请选择:");
        int choose = input.nextInt();
        switch (choose) {
            case 1:
                showLogin();
                break;
            case 2:
                showRegister();
                break;
            case 3:
                System.out.println("谢谢使用");
                break;
            default:
                System.out.println("输入有误");
                System.exit(0);
        }
    }

    public void showUploadFile() {
        System.out.println("请输入文件上传的绝对路径(例如:e:/1.jpg)");
        String path = input.next();
        File file = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        String fname = path.substring(path.lastIndexOf("/") + 1);

        try {
            fis = new FileInputStream(path);
            byte[] fcontent = new byte[fis.available()];
            bis = new BufferedInputStream(fis);
            bis.read(fcontent);
            file = new File(fname, fcontent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CommandTransfer transfer = new CommandTransfer();
        transfer.setCmd("uploadFile");
        transfer.setData(file);

        try {
            socket = new Socket("127.0.0.1", 8800);
            // 发送数据给服务端
            sendData(transfer);
            // 获取服务端发回的反馈
            transfer = getData();
            System.out.println(transfer.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void showRegister() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        while (true) {
            System.out.println("请输入用户名");
            user.setUserName(input.next());
            System.out.println("请输入密码");
            user.setPassWord(input.next());
            System.out.println("请再次输入密码");
            String repassword = input.next();
            if (!user.getPassWord().equals(repassword)) {
                System.out.println("两次密码不一致");
                System.out.println("**************");
                continue;
            }
            transfer.setCmd("register");
            transfer.setData(user);

            try {
                socket = new Socket("127.0.0.1", 8800);
                // 发送数据给服务端
                sendData(transfer);
                // 获取服务端发回的反馈
                transfer = getData();
                System.out.println(transfer.getResult());
                System.out.println("***************");
                if (transfer.isFlag()) {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }
        }
        showLogin();
    }

    private void sendData(CommandTransfer transfer) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeAll() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLogin() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        int count = 0;
        while (true) {
            count++;
            if (count > 3) {
                System.out.println("您已经三次失败了...请重新启动尝试");
                System.exit(0);
            }
            System.out.println("请输入用户名");
            user.setUserName(input.next());
            System.out.println("请输入密码");
            user.setPassWord(input.next());
            transfer.setCmd("login");
            transfer.setData(user);

            try {
                socket = new Socket("127.0.0.1", 8800);
                // 将数据发送到服务器端
                sendData(transfer);
                // 获取服务器返回的数据
                transfer = getData();
                System.out.println(transfer.getResult());
                System.out.println("***************");
                if (transfer.isFlag()) {
                    // 如果登录成功，则不用再登录
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }
        }
        showUploadFile();
    }

    public CommandTransfer getData() {
        ObjectInputStream ois = null;
        CommandTransfer transfer = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            transfer = (CommandTransfer) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transfer;
    }
}
