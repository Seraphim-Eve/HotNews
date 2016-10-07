package com.springapp.mvc;

import com.springapp.mvc.bean.Blog;
import com.springapp.mvc.bean.User;
import com.springapp.mvc.impl.HotNewsImpl;
import com.springapp.mvc.service.HotNews;
import com.springapp.mvc.utils.*;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MController {

    /**
     * 登陆超时跳转主页
     * @return
     */
    @RequestMapping(value = "indexTimeout.do", method = RequestMethod.GET)
    public String loginTimeout(@RequestParam("loginTimeout") String loginTimeout, ModelMap modelMap) {
        if (null != loginTimeout && !loginTimeout.isEmpty()) {
            modelMap.addAttribute("msg", "登陆超时,请重新登陆!");
        }
        return "index";
    }

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "index.do")
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
            return "forward:main.do"; //登陆到主页
        }

        //重定向到index.jsp页面
        modelMap.addAttribute("msg", "输入的用户名或密码错误!");
        modelMap.addAttribute("email", email);
        return "index";
    }

    /**
     * 博客内容列表
     * @param user
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "blog.do")
    public String blog(@ModelAttribute User user, ModelMap modelMap) throws SQLException {
        //TODO 读取博客内容

        String author = user.getEmail();

        String sql =
                "select 'true' as flag, id, title, IFNULL(discuss_id, 0) as discuss_id, IFNULL(good_id, 0) as good_id from blog where author = '" + author + "' " + //作者本身所有的博客
                "union all " +
                "select 'false' as flag, id, title, IFNULL(discuss_id, 0) as discuss_id, IFNULL(good_id, 0) as good_id from blog where authority = 'public' and author != '" + author + "'"; //其他公开的博客

        Connection conn = MySQLUtils.getConn();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        //博客列表
        List<Blog> list = new ArrayList<Blog>();

        while (rs.next()) {
            Blog blog = new Blog();

            String flag = rs.getString("flag");
            blog.setFlag(flag);

            String id = rs.getString("id");
            blog.setId(id);

            String title = rs.getString("title");
            blog.setTitle(title);

            String discuss_id = rs.getString("discuss_id");
            if (!discuss_id.equals("0")) {
                //查询讨论表
                sql = "select count(*) as discuss_num from discuss where id = '" + discuss_id + "'";
                //如果不新创建stmt就会抛Operation not allowed after ResultSet closed!
                //在重复使用stmt.executeQuery(sql)的时候就会关闭上一个ResultSet.
                Statement st = conn.createStatement();
                ResultSet tmp = st.executeQuery(sql);
                int discuss_num = Integer.valueOf(tmp.getString("discuss_num"));
                blog.setDiscuss_num(discuss_num);
            } else {
                //讨论数为0
                blog.setDiscuss_num(0);
            }

            String good_id = rs.getString("good_id");
            if (!good_id.equals("0")) {
                //查询点赞表
                sql = "select count(*) as good_num from good where id = '" + good_id + "'";
                Statement st = conn.createStatement();
                ResultSet tmp = st.executeQuery(sql);
                while (tmp.next()) {
                    int good_num = Integer.valueOf(tmp.getString("good_num"));
                    blog.setGood_num(good_num);
                }
            } else {
                //点赞数为0
                blog.setGood_num(0);
            }

            list.add(blog);
        }

        //存数据
        modelMap.put("blog_list", list);

        return "blog";
    }

    /**
     * 博客查看
     * @return
     */
    @RequestMapping(value = "blog_view.do", method = RequestMethod.GET)
    public String blogView(@RequestParam String id, ModelMap modelMap, HttpSession httpSession) throws SQLException {
        //TODO 准备博客数据及评论
        String sql = "select title, IFNULL(discuss_id,0) as discuss_id, IFNULL(good_id, 0) as good_id, content from blog where id = '" + id + "'";
        Connection conn = MySQLUtils.getConn();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        //当前用户名
        String username = httpSession.getAttribute("username").toString();

        while (rs.next()) {
            String title = rs.getString("title");
            String content = rs.getString("content");
            modelMap.put("title", title);
            modelMap.put("content", content);
            String discuss_id = rs.getString("discuss_id");
            String good_id = rs.getString("good_id");
            if (!discuss_id.equals("0")) {
                sql = "select d_user, content from discuss where id = '" + discuss_id + "'";
                //TODO 读取评论列表
            }

            if (!good_id.equals("0")) {
                sql = "select good_username from good where id = '" + good_id + "'";
                Statement st = conn.createStatement();
                ResultSet tmp = st.executeQuery(sql);
                int count = 0;
                int max = 0; //共多少条点赞数据
                while (tmp.next()) {
                    max = tmp.getRow();
                    String g_username = tmp.getString("good_username");
                    if (!g_username.isEmpty() && g_username.equals(username)) {
                        //当前用户不能重复赞
                        modelMap.put("good", "<button id='good' type='button' class='btn btn-default btn-lg' disabled='disabled'>\n" +
                                "                    <span class='glyphicon glyphicon-thumbs-up'></span>\n" +
                                "                </button>");
                        break;
                    } else {
                        count++;
                    }
                }

                if (count != 0 && max != 0 && count == max) {
                    //没有当前用户的点赞记录
                    modelMap.put("good", "<button id='good' type='button' class='btn btn-default btn-lg'>\n" +
                            "                    <span class='glyphicon glyphicon-thumbs-up'></span>\n" +
                            "                </button>");
                }

            } else {
                //当前用户可以赞(当前文章还未被点赞)
                modelMap.put("good", "<button id='good' type='button' class='btn btn-default btn-lg'>\n" +
                        "                    <span class='glyphicon glyphicon-thumbs-up'></span>\n" +
                        "                </button>");
            }
        }

        //博客id
        modelMap.put("id", id);

        return "blog_view";
    }


    /**
     * 博客点赞请求
     * 返还code解释: 100: 已经点过赞. 200: 点赞成功. 300: 点赞失败.
     * @param blog_id
     * @return
     */
    @RequestMapping(value = "blog_good.do", method = RequestMethod.POST)
    @ResponseBody
    public String blogGood(@RequestParam String blog_id, HttpSession httpSession) throws SQLException {

        String username = httpSession.getAttribute("username").toString();
        String sql = "select IFNULL(good_id, 0) as good_id from blog where id = '" + blog_id  + "'";
        Connection conn = MySQLUtils.getConn();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String good_id = rs.getString("good_id");
            if (!good_id.equals("0")) {
                sql = "select * from good where good_username = '" + username + "' and id = '" + good_id + "'";
                Statement st = conn.createStatement();
                ResultSet tmp = st.executeQuery(sql);

                int row = 0;
                if (tmp != null) {
                    tmp.beforeFirst();
                    tmp.last();
                    row = tmp.getRow();
                }

                if (row > 0) {
                    //已经点过赞了
                    return "100";
                }

                //存点赞用户
                sql = "insert into good(id, good_username) values('" + good_id + "', '" + username + "')";
                String code = "";
                if (MySQLUtils.insert(sql)) {
                    code = "200";
                } else {
                    code = "300";
                }
                return code;

            } else {

                //没有good_id
                String g_id = "good_" + UUIDUtils.code();
                sql = "insert into good(id, good_username) values('" + g_id + "', '" + username + "')";
                String code = "";
                if (MySQLUtils.insert(sql)) {
                    //关联good_id与blog_id;
                    sql = "update blog set good_id = '" + g_id + "' where id = '" + blog_id + "'";
                    if (MySQLUtils.insert(sql)) {
                        System.out.println("blog_id: " + blog_id + " 与 " + "good_id: " + g_id + " 关联成功!");
                    } else {
                        System.out.println("blog_id: " + blog_id + " 与 " + "good_id: " + g_id + " 关联失败!");
                    }
                    code = "200";
                } else {
                    code = "300";
                }
                return code;

            }
        }
        return "";
    }


    /**
     * 博客编辑跳转
     * @return
     */
    @RequestMapping(value = "blog_editor.do", method = RequestMethod.GET)
    public String blogEditor(ModelMap modelMap) {
        modelMap.put("blog_content", "输入博客内容.");
        modelMap.put("option", "<option value='private'>私有</option><option value='public'>公开</option>");
        return "blog_editor";
    }


    /**
     * 删除博客请求
     * @param blog_id
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "blog_delete.do", method = RequestMethod.POST)
    public String blogDelete(@RequestParam String blog_id) throws SQLException{

        String sql = "delete from blog where id='" + blog_id + "'";
        if (MySQLUtils.insert(sql)) {
            System.out.println("删除博客id:" + blog_id + " 成功!");
        } else {
            System.out.println("删除博客id:" + blog_id + " 失败!");
        }
        //TODO 页面未展现出最新的数据
        return "forward:blog.do";
    }

    /**
     * 博客创建
     * @param blog
     * @return
     */
    @RequestMapping(value = "blog_create.do", method= RequestMethod.POST)
    public String blogCreate(@ModelAttribute Blog blog, HttpSession session, ModelMap modelMap) throws SQLException {
        //博客id
        String blog_id = "blog_" + UUIDUtils.code();
        blog.setId(blog_id);

        //博客创建时间
        String create_time = DateUtils.getCurrDate();
        blog.setCreate_time(create_time);

        //博客作者
        String author = session.getAttribute("username").toString();
        blog.setAuthor(author);

        String sql = "insert into blog(id, title, create_time, author, authority, content) values('${id}', '${title}', '${create_time}', '${author}', '${authority}', '${content}')";

        sql = StringUtils.getString(sql, blog);

        if (MySQLUtils.insert(sql)) {
            modelMap.put("msg", "插入博客成功!");
            modelMap.put("blog_content", "输入博客内容.");
        } else {
            modelMap.put("msg", "插入博客失败!");
            modelMap.put("blog_content", blog.getContent());
            modelMap.put("title", blog.getTitle());
            if (blog.getAuthority().equals("private")) {
                modelMap.put("option", "<option value='private' selected>私有</option><option value='public'>公开</option>");
            } else {
                modelMap.put("option", "<option value='private'>私有</option><option value='public' selected>公开</option>");
            }
        }
        return "blog_editor";
    }

    /**
     * 新闻页面
     * @param user
     * @return
     */
    @RequestMapping(value = "news.do")
    public String news(@ModelAttribute User user, ModelMap modelMap) {
        //准备tab中News数据显示
        //主页显示16条新闻
        HotNews hn = new HotNewsImpl();
        ArrayList<String> hotNews = hn.getHotNews();
        modelMap.addAttribute("hotNews", hotNews);
        return "news";
    }

    /**
     * 登陆到主页面
     * @param user
     * @param modelMap
     * @return 主页面
     * @throws SQLException
     */
    @RequestMapping(value = "main.do")
    public String main(@ModelAttribute User user, ModelMap modelMap) throws SQLException {
        String sql = "select * from Users where username = '" + user.getEmail() + "' and password = '" + MD5Utils.getMD5(user.getPassword()) + "'";
        User u = MySQLUtils.queryForUser(sql);
        modelMap.addAttribute("nickname", u.getNickname());
        return "main";
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
     * 登陆主页后密码修改页面跳转
     * @return
     */
    @RequestMapping(value = "reset_password_jump.do")
    public String resetPassword() {
        return "reset_password";
    }

    /**
     * 登陆主页后密码修改页面提交
     * @return
     */
    @RequestMapping(value = "reset_password.do")
    public String resetPassword2(@RequestParam("s_password") String s_password, @RequestParam("n_password") String n_password,
                                 HttpSession session, ModelMap modelMap) throws SQLException {

        //原密码
        s_password = MD5Utils.getMD5(s_password);
        //新密码
        n_password = MD5Utils.getMD5(n_password);

        //用户名
        String username = session.getAttribute("username").toString();

        String sql = "update Users set password = '" + n_password + "' where username = '" + username + "' and password = '" + s_password + "'";

        if (MySQLUtils.insert(sql)) {
            //session失效
            session.invalidate();
            modelMap.addAttribute("msg", "密码修改成功!请重新登陆!");
            return "forward:index.do";
        } else {
            modelMap.addAttribute("msg", "密码修改失败!");
        }
        return "reset_password";
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index.do";
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

    /**
     * 检查密码是否正确
     * @return true: 密码正确, false: 密码错误
     */
    @RequestMapping(value = "check_password.do", method = RequestMethod.POST)
    @ResponseBody
    public String checkPassword(@RequestParam("password") String password, HttpSession session) throws SQLException {

        password = MD5Utils.getMD5(password);
        String username = session.getAttribute("username").toString();

        String sql = "select * from Users where username = '" + username + "' and password = '" + password +"'";

        if (MySQLUtils.queryEmail(sql)) {
            return "true";
        }
        return "false";
    }
}