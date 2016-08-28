package com.springapp.mvc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
