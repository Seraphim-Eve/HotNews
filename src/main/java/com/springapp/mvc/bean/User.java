package com.springapp.mvc.bean;

/**
 * Created by Xr on 2016/8/25.
 */
public class User {

    /**
     * 代表系统的username
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    @Deprecated
    private String username;

    /**
     * 用户密码MD5
     */
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
