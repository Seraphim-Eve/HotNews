package com.springapp.mvc;

import com.springapp.mvc.bean.User;
import com.springapp.mvc.impl.HotNewsImpl;
import com.springapp.mvc.service.HotNews;
import com.springapp.mvc.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class MController {

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 登陆检查
     * @param user 映射User用户
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute User user, ModelMap modelMap, HttpSession session) throws SQLException {

        String email = user.getEmail();
        String password = MD5Utils.getMD5(user.getPassword());
        String sql = "select * from Users where username = '" + email + "' and password = '" + password + "'";

        if (MySQLUtils.queryEmail(sql)) {
            session.setAttribute("username", email);
            //更新last_login_time字段
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            sql = "update Users set last_login_time = '" + time  + "' where username = '" + email + "'";
            MySQLUtils.insert(sql);
            return "forward:blog.do"; //登陆到主页
        }

        //重定向到index.jsp页面
        modelMap.addAttribute("msg", "输入的用户名或密码错误!");
        modelMap.addAttribute("email", email);
        return "index";
    }

    /**
     * 博客为首页
     * @return
     */
    @RequestMapping(value = "blog.do")
    public String blog(@ModelAttribute User user) {
        return "blog";
    }

    /**
     * 登陆到主页面
     * @param user
     * @param modelMap
     * @return 主页面
     * @throws SQLException
     */
    @RequestMapping(value = "news.do")
    public String news(@ModelAttribute User user, ModelMap modelMap) throws SQLException {
        String sql = "select * from Users where username = '" + user.getEmail() + "' and password = '" + MD5Utils.getMD5(user.getPassword()) + "'";
        User u = MySQLUtils.queryForUser(sql);
        modelMap.addAttribute("nickname", u.getNickname());

        //TODO 准备登陆主页后的数据显示
        //主页显示18条新闻
        HotNews hn = new HotNewsImpl();
        ArrayList<String> hotNews = hn.getHotNews();
        modelMap.addAttribute("hotNews", hotNews);

        return "news";
    }

    /**
     * 查看(测试session)
     * @return
     */
    @RequestMapping(value = "view.do", method = RequestMethod.GET)
    public String view() {
        return "view";
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

        String sql = "insert into Users(email, nickname, username, password, reg_time) values('${email}', '${nickname}', '${username}', '${password}', '${reg_time}')";
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
     * 重置密码邮件发送
     * @param user
     * @return
     */
    @RequestMapping(value = "forgot.do", method = RequestMethod.POST)
    public String forgot(@ModelAttribute User user, HttpServletRequest request) {
        try {
            //为了防止重复提交更改密码
            //检查reset_code是否为空
            if (null != user.getEmail() && !user.getEmail().isEmpty()) {
                String sql = "select * from Users where username = '" + user.getEmail() + "' and reset_code is null";
                if (MySQLUtils.queryEmail(sql)) {
                    //reset_code为空: 则发送重置密码邮件
                    String path = request.getContextPath();
                    String website_host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

                    String code = UUIDUtils.code();

                    MailUtils.sendMail(user.getEmail(), website_host, code);

                    sql = "update Users set reset_code = '" + code + "' where username = '" + user.getEmail() + "'";
                    MySQLUtils.insert(sql);

                    //启动一个线程计算重置密码code失效时间
                    PlanTask pt = new PlanTask(user.getEmail());
                } else {
                    //reset_code不为空: 则不发送重置密码邮件,跳转到提示已经发送密码修改邮件.
                    return "reset_repeat";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "reset_error";
        }
        return "reset_success";
    }

    /**
     * code验证
     * 处理邮件中点击过来的密码重置页面
     * @param email
     * @param code
     * @return
     */
    @RequestMapping(value = "reset.do", method = RequestMethod.GET)
    public String reset(@RequestParam("email") String email, @RequestParam("code") String code, ModelMap modelMap) {

        if (null != email && !email.isEmpty() && null != code && !code.isEmpty()) {

            String sql = "select * from Users where email = '" + email + "' and reset_code = '" + code + "'";
            try {
                if (MySQLUtils.queryEmail(sql)) {
                    //code有效 -> effective_code
                    modelMap.addAttribute("email", email);
                    modelMap.addAttribute("reset_code", code);
                    return "effective_code";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //code无效 -> failure_code
        return "failure_code";
    }

    /**
     * 重置密码
     * @return
     */
    @RequestMapping(value = "effective_code.do", method = RequestMethod.POST)
    public String effectiveCode(@ModelAttribute User user) throws SQLException {

        if (null != user.getEmail() && !user.getEmail().isEmpty() && null != user.getReset_code() && !user.getReset_code().isEmpty()) {
            String sql = "select * from Users where email = '" + user.getEmail() + "' and reset_code = '" + user.getReset_code() + "'";
            try {
                if (MySQLUtils.queryEmail(sql)) {
                    //修改密码,设置reset_code为null(设置为null防止重复提交页面修改密码.)
                    sql = "update Users set password = '" + MD5Utils.getMD5(user.getPassword()) + "', reset_code = null where username = '" + user.getEmail() + "' and reset_code = '" + user.getReset_code() + "'";
                    MySQLUtils.insert(sql);
                    return "effective_success";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "failure_code";
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    /**
     * 邮箱是否存在检查
     * @param email 检查的邮箱
     * @return true: 邮箱存在, false: 邮箱不存在.
     */
    @RequestMapping(value = "check.do", method = RequestMethod.POST)
    @ResponseBody
    public String checkMail(@RequestParam("email") String email) throws SQLException {
        String sql = "select * from Users where username = '" + email + "'";
        if (MySQLUtils.queryEmail(sql)) {
            return "true";
        }
        return "false";
    }
}