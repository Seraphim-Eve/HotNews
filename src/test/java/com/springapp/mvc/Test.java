package com.springapp.mvc;

import com.springapp.mvc.utils.MD5Utils;
import com.springapp.mvc.utils.MySQLUtils;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Xr on 2016/8/25.
 */
public class Test {

    @org.junit.Test
    public void t1() throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String str = "rui.xiao@google.com";
        byte[] digest = md5.digest(str.getBytes("UTF-8"));
        System.out.println(DigestUtils.md5DigestAsHex(digest));
    }

    @org.junit.Test
    public void t2() {
        System.out.println(MD5Utils.getMD5("rui.xiao@google.com"));
    }

    @org.junit.Test
    public void t3() {
        Connection conn = MySQLUtils.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Users";
            ResultSet resultSet = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
