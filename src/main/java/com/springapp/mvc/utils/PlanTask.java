package com.springapp.mvc.utils;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Xr on 2016/9/2.
 * 计划任务
 * //1秒 = 1000毫秒
   //1分钟 = 60秒

 */
public class PlanTask {

    /**
     * 清空用户email的reset_code
     * @param email
     */
    public PlanTask(final String email) {
        Timer timer = new Timer();
        //30分钟
        long time = 30 * 60 * 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String sql = "update Users set reset_code = null where username = '" + email + "'";
                try {
                    MySQLUtils.insert(sql);
                    System.out.println("清空" + email + "的reset_code!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, time);
    }
}
