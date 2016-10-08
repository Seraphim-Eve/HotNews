package com.springapp.mvc.utils;

import java.util.UUID;

/**
 * Created by Xr on 2016/9/2.
 * 生成密码重置验证code.
 */
public class UUIDUtils {

    /**
     * 生成code
     * @return
     */
    public static String code() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
