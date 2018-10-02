package com.ecjtu.fileuploaddemo.socket;

import com.ecjtu.fileuploaddemo.entity.File;
import com.ecjtu.fileuploaddemo.entity.User;
import com.ecjtu.fileuploaddemo.service.FileService;
import com.ecjtu.fileuploaddemo.service.UserService;
import com.ecjtu.fileuploaddemo.util.CommandTransfer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * 多线程情况下的服务端
 */
public class ServerThread extends Thread {
    private Socket socket;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private UserService userService = new UserService();
    private FileService fileService = new FileService();

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            CommandTransfer transfer = (CommandTransfer) ois.readObject();
            // 执行客户端发送到服务器的指令操作
            transfer = execute(transfer);
            // 响应客户端
            oos.writeObject(transfer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CommandTransfer execute(CommandTransfer transfer) throws SQLException {
        // 获取当前操作的指令
        String cmd = transfer.getCmd();
        if ("login".equals(cmd)) {
            User user = (User) transfer.getData();
            boolean flag = userService.login(user);
            transfer.setFlag(flag);
            if (flag) {
                transfer.setResult("登录成功");

            } else {
                transfer.setResult("用户名或密码错误");
            }
        } else if ("register".equals(cmd)) {
            User user = (User) transfer.getData();
            userService.register(user);
            boolean flag = userService.login(user);
            transfer.setFlag(flag);
            if (flag) {
                transfer.setResult("注册成功");
            } else {
                transfer.setResult("注册失败..");
            }
        } else if ("uploadFile".equals(cmd)) {
            File file = (File) transfer.getData();
            fileService.save(file);
            transfer.setResult("上传成功");
        }
        return transfer;
    }
}
