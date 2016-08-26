package com.springapp.mvc.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * Created by Xr on 2016/8/25.
 */
public class MD5Utils {

    /**
     * 得到str的md5值
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes("UTF-8"));
            return DigestUtils.md5DigestAsHex(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
