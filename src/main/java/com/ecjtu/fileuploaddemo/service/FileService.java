package com.ecjtu.fileuploaddemo.service;

import com.ecjtu.fileuploaddemo.entity.File;
import com.ecjtu.fileuploaddemo.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 将文件保存到数据库中的操作
 */
public class FileService {

    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;

    // 保存到数据库中
    public void save(File file) {

        try {
            String sql = "insert into file(fname,fcontent) values(?,?)";
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, file.getFname());
            psmt.setBytes(2, file.getFcontent());
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, psmt, conn);
        }


    }
}
