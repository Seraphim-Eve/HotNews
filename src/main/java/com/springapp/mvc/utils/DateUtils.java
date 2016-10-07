package com.springapp.mvc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Xr on 2016/10/5.
 */
public class DateUtils {

    /**
     * 得到当前时间 2016-10-05 12:52:00
     * @return
     */
    public static String getCurrDate() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }

}
