package com.springapp.mvc;

import com.springapp.mvc.bean.User;
import com.springapp.mvc.utils.MySQLUtils;
import com.springapp.mvc.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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
            return "register_success";
        } else {
            //insert error
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


    @RequestMapping(value = "check.do", method = RequestMethod.POST)
    @ResponseBody
    public String checkMail(@RequestParam("email") String email) {
        //TODO 检查邮箱是否存在.
        return email;
    }
}