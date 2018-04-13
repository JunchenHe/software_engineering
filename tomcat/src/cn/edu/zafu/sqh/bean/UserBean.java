package cn.edu.zafu.sqh.bean;

public class UserBean {
    /**
     * 存储了用户的id
     */
    public int userid;
    /**
     * 用户的等级
     */
    public int level;

    /**
     * 存储了用户名
     */
    public String username = "";
    /**
     * 存储了用户的密码
     * 只在登录的时候有用
     */
    public String password = "";

    /**
     * 存储了是否成功登录
     */
    public boolean login;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
