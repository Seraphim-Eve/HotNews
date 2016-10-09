package com.springapp.mvc.bean;

/**
 * Created by Xr on 2016/10/9.
 * 评论实体
 */
public class Discuss {
    //评论id
    public String id;
    //评论时间
    public String time;
    //评论用户(昵称)
    public String d_user;
    //评论内容
    public String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getD_user() {
        return d_user;
    }

    public void setD_user(String d_user) {
        this.d_user = d_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
