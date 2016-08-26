package com.springapp.mvc;

import com.springapp.mvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String register(@ModelAttribute User user) {

        //TODO 插入新增的用户到mysql

        return "news";
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