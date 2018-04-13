package cn.edu.zafu.sqh.bean;

import java.sql.Timestamp;

public class MessageBean {
    /**
     * 存储了消息的等级
     */
    private int level;
    /**
     * 存储了消息的键
     */
    private String time;

    /**
     * 存储了消息的内容
     */
    private String note;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
