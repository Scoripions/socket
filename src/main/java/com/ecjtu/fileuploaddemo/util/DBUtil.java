package com.ecjtu.fileuploaddemo.util;

import java.sql.*;

/**
 * 数据库连接的工具类
 */
public class DBUtil {
    public static Connection getConnection() {

        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/socket";
        String username = "root";
        String password = "xxx";
        Connection conn = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return conn;
        }
    }

    public static void closeAll(ResultSet rs, PreparedStatement psmt,Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
