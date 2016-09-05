package com.springapp.mvc.service;

import java.util.ArrayList;

/**
 * Created by Xr on 2016/9/5.
 * 数据收集接口
 */
public interface HotNews {

    /**
     * 得到热门数据新闻
     * @return
     */
    public ArrayList<String> getHotNews();

}
