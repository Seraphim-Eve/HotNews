package com.springapp.mvc.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by Xr on 2016/9/28.
 */
public class Blog {

    //博客身份标示
    public String flag;

    //博客id
    public String id;

    //博客标题
    public String title;

    //博客作者
    public String author;

    //博客创建时间
    public String create_time;

    //博客更新时间
    public String update_time;


    //博客评论id
    public String discuss_id;

    //博客点赞id
    public String good_id;

    //博客评论数
    public int discuss_num;

    //博客点赞数
    public int good_num;

    //博客权限
    public String authority;

    //博客内容
    public String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDiscuss_id() {
        return discuss_id;
    }

    public void setDiscuss_id(String discuss_id) {
        this.discuss_id = discuss_id;
    }

    public String getGood_id() {
        return good_id;
    }

    public void setGood_id(String good_id) {
        this.good_id = good_id;
    }

    public int getDiscuss_num() {
        return discuss_num;
    }

    public void setDiscuss_num(int discuss_num) {
        this.discuss_num = discuss_num;
    }

    public int getGood_num() {
        return good_num;
    }

    public void setGood_num(int good_num) {
        this.good_num = good_num;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
