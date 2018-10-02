package com.ecjtu.fileuploaddemo.service;

import com.ecjtu.fileuploaddemo.entity.User;
import com.ecjtu.fileuploaddemo.util.DBUtil;

import java.sql.*;

/**
 * 将用户保存到数据库中的操作
 */
public class UserService {

    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;

    // 保存到数据库中
    public void register(User user) {

        try {
            String sql = "insert into user(username,password) values(?,?)";
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getPassWord());
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, psmt, conn);
        }
    }

    public boolean login(User user) throws SQLException {
        try {
            String sql = "select * from user where username = ? and password = ?";
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getPassWord());
            rs = psmt.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, psmt, conn);
        }
        return false;
    }
}
