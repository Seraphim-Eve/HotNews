package com.springapp.mvc.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by Xr on 2016/9/28.
 */
public class Blog {
    //博客标题
    public String title;

    //博客作者
    public String author;

    //博客创建时间
    public String create_time;

    //博客更新时间
    public String update_time;

    //博客赞数
    public int good;

    //博客权限
    public String authority;

    //博客内容
    public String content;

    //博客评论
    public List<Map<String, String>> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    /**
     * map<String user, String comment>
     * user: 评论用户
     * comment: 评论内容
     * @param list
     */
    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }
}
