package com.springapp.mvc.utils;

import com.springapp.mvc.bean.User;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Xr on 2016/8/25.
 */
public class MySQLUtils {

    private static Connection conn = null;

    static {
        Properties pp = new Properties();
        try {

            Class.forName("com.mysql.jdbc.Driver");

            pp.load(MySQLUtils.class.getClassLoader().getResourceAsStream("mysql.properties"));

            String url = pp.getProperty("db.url");
            String username = pp.getProperty("db.username");
            String password = pp.getProperty("db.password");

            conn = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get mysql connect
     * @return
     */
    public static Connection getConn() {
        return conn;
    }


    /**
     * insert data to users table.
     * @param sql
     * @return 是否成功插入
     */
    public static boolean insert(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql) > 0;
    }

    /**
     * 检查邮箱是否存在
     * @param sql
     * @return true: 存在, false: 不存在.
     */
    public static boolean queryEmail(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int size = 0;
        if (rs != null) {
            rs.beforeFirst();
            rs.last();
            size = rs.getRow();
        }
        return size > 0;
    }

    /**
     * 查询用户
     * @param sql 查询SQL
     * @return 返回用户
     */
    public static User queryForUser(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        User user = null;
        if (rs.next()) {
            String nickname = rs.getString("nickname");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String reg_time = rs.getString("reg_time");
            String reset_code = StringUtils.nvl(rs.getString("reset_code"));
            String last_login_time = rs.getString("last_login_time");

            user = new User();
            user.setEmail(username);
            user.setUsername(username);
            user.setNickname(nickname);
            user.setPassword(password);
            user.setReg_time(reg_time);
            user.setReset_code(reset_code);
            user.setLast_login_time(last_login_time);

        }
        return user;
    }

    /**
     * close mysql connect
     */
    public static void closeConn() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
