package com.springapp.mvc.utils;

import com.springapp.mvc.bean.User;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Xr on 2016/8/27.
 */
public class StringUtils {

    private static Properties props = new Properties();

    static {
        props.setProperty(Velocity.INPUT_ENCODING, "GBK");
        props.setProperty(Velocity.RESOURCE_LOADER, "class");
        props.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    }

    /**
     * User用户字符串替换
     * @param str 需要替换的字符串
     * @param user User实例
     * @return 替换好后的字符串
     */
    public static String getString(String str, User user) {

        VelocityEngine ve = new VelocityEngine(props);
        VelocityContext vc = new VelocityContext();

        vc.put("email", user.getEmail());
        vc.put("nickname", user.getNickname());
        vc.put("username", user.getEmail());
        vc.put("password", MD5Utils.getMD5(user.getPassword()));
        vc.put("reg_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        StringWriter sw = new StringWriter();
        ve.evaluate(vc, sw, "", str);

        return sw.toString();
    }

    /**
     * 判断空值
     * @param str
     * @return 如果值str是空或者"",返回"",否则返回str
     */
    public static String nvl(String str) {
        if (null != str && !str.isEmpty()) {
            return str;
        }
        return "";
    }
}
