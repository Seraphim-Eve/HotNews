package com.springapp.mvc.utils;

import com.springapp.mvc.bean.User;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Xr on 2016/8/26.
 */
public class MailUtils {

    //get username and password
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(MailUtils.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     * @param emailAddress 收件人
     * @throws Exception
     */
    public static void sendMail(String emailAddress, String website_host, String code) throws Exception{
        String host = prop.getProperty("host");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", host);
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);

        MimeMessage mimeMessage = createMime(session, username, emailAddress, website_host, code);

        Transport transport = session.getTransport();

        transport.connect(username, password);

        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        transport.close();

    }

    /**
     * 创建发送邮件主体
     * @param session
     * @param sendMail 发送人
     * @param receiveMail 收件人
     * @return
     */
    private static MimeMessage createMime(Session session, String sendMail, String receiveMail, String website_host, String code) throws Exception {
        MimeMessage message = new MimeMessage(session);

        //设置发送人
        message.setFrom(new InternetAddress(sendMail, "HotNews", "UTF-8"));

        String sql = "select * from Users where username = '" + receiveMail + "'";
        User user = MySQLUtils.queryForUser(sql);

        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail, user.getNickname(), "UTF-8"));

        //设置主题
        message.setSubject("HotNews密码重置", "UTF-8");

        //组合url(receiveMail,code)
        //http://www.ruixiao.org:80/HotNews
        String url = website_host + "/reset.do?email=" + receiveMail + "&code=" + code;

        String html = "<div bgcolor=\"#FFFFFF\" leftmargin=\"0\" marginheight=\"0\" marginwidth=\"0\" topmargin=\"0\"> \n" +
                "   <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"680\"> \n" +
                "    <tbody><tr> \n" +
                "     <td style=\"border-bottom:2px solid #d30403\"><h1>HotNews</h1></td> \n" +
                "    </tr> \n" +
                "    <tr> \n" +
                "     <td align=\"center\" colspan=\"2\">\n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"680\"> \n" +
                "       <tbody><tr> \n" +
                "        <td style=\"color:#333;line-height:28px;font-size:14px;font-family:Verdana,'宋体';\"> 亲爱的用户：" + user.getEmail() + " 您好<br> <br> 您的密码修改链接如下：<br> <br> <a href=\"" + url + "\" target=\"_blank\">" + (website_host + "/reset") + "</a><br> <br> 本邮件请使用HTML方式显示，否则以上链接可能无法正确显示。<br> <br> 如果点击以上链接不能进入，请把以上链接复制粘贴到浏览器的地址栏，然后回车来执行此链接。<br> <br> 此链接半小时内有效，如链接失效请到网站重新操作！ </td> \n" +
                "       </tr> \n" +
                "      </tbody></table></td> \n" +
                "    </tr>\n" +
                "   </tbody></table> \n" +
                "  </div>";

        //设置邮件内容
        message.setContent(html, "text/html;charset=UTF-8");

        //设置发送时间
        message.setSentDate(new Date());

        //设置保存
        message.saveChanges();

        return message;
    }
}
