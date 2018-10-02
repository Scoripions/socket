package com.ecjtu.fileuploaddemo.socket;

/**
 * 开启客户端
 */
public class StartClient {
    public static void main(String[] args) {
        Client client = new Client();
        client.showMainMenu();
    }
}
