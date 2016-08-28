package com.springapp.mvc;

import com.springapp.mvc.bean.User;
import com.springapp.mvc.utils.MD5Utils;
import com.springapp.mvc.utils.MySQLUtils;
import com.springapp.mvc.utils.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MController {

    /**
     * 登陆检查
     * @param user 映射User用户
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute User user, ModelMap modelMap) {

        modelMap.addAttribute("email", user.getEmail());

        //TODO 密码需要编码为MD5,与数据库做对比
        modelMap.addAttribute("password", user.getPassword());
        modelMap.addAttribute("nickname", "Rui Shawn");

        return "news";
    }

    /**
     * 用户注册跳转
     * @return
     */
    @RequestMapping(value = "registerJump.do", method = RequestMethod.GET)
    public String registerJump() {
        return "register";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, ModelMap modelMap) throws SQLException {

        String sql = "insert into Users(nickname, username, password, last_update_time) values('${nickname}', '${username}', '${password}', '${last_update_time}')";
        sql = StringUtils.getString(sql, user);

        if (MySQLUtils.insert(sql)) {
            //insert success
            //TODO 插入成功跳转到插入成功页面.jsp(页面中5秒自动跳入到登陆页面)
            return "register_success";
        } else {
            //insert error
            //TODO 插入失败跳转到插入失败.jsp(页面中5秒自动跳入到注册页面)
            return "register_error";
        }

    }


    /**
     * 忘记密码跳转
     * @param user
     * @return
     */
    @RequestMapping(value = "forgotJump.do", method = RequestMethod.GET)
    public String forgotJump(@ModelAttribute User user) {
        return "forget";
    }

    /**
     * 重置密码
     * @param user
     * @return
     */
    @RequestMapping(value = "forgot.do", method = RequestMethod.GET)
    public String forgot(@ModelAttribute User user) {
        return "hello";
    }


}