package com.ecjtu.fileuploaddemo.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * 因为要在网络中进行传输，实现序列化接口
 */
public class User implements Serializable{
    private int id;
    private String userName;
    private String passWord;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
