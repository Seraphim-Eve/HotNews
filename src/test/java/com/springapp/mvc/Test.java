package com.springapp.mvc;

import com.springapp.mvc.bean.User;
import com.springapp.mvc.utils.MD5Utils;
import com.springapp.mvc.utils.MySQLUtils;
import com.springapp.mvc.utils.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.util.DigestUtils;

import java.io.StringWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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

    @org.junit.Test
    public void t4() {
        String sql = "insert into Users(nickname, username, password, last_update_time) values('${nickname}', '${username}', '${password}', '${last_update_time}')";
        String password = MD5Utils.getMD5("xiaorui");

        VelocityEngine ve = new VelocityEngine();
        VelocityContext vc = new VelocityContext();

        //TODO 插入新增的用户到mysql

        vc.put("username", "Rui Shawn");
        vc.put("password", password);
        vc.put("last_update_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        StringWriter sw = new StringWriter();
        ve.evaluate(vc, sw, "", sql);
        System.out.println(sw.toString());

    }

    @org.junit.Test
    public void t5() {
        String sql = "insert into Users(nickname, username, password, last_update_time) values('${nickname}', '${username}', '${password}', '${last_update_time}')";
        User user = new User();
        user.setEmail("rui.xiao@google.com");
        user.setNickname("Rui Shawn");
        user.setPassword("xiaorui");
        System.out.println(StringUtils.getString(sql, user));
    }
}
