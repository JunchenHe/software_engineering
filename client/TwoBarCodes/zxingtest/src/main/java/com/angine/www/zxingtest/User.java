package com.angine.www.zxingtest;

/**
 * Created by asus-pc on 2018/4/22.
 */

public class User {
    private String userid;
    private String level;
    private String username;
    private String passwordl;
    private boolean login;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordl() {
        return passwordl;
    }

    public void setPasswordl(String passwordl) {
        this.passwordl = passwordl;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
