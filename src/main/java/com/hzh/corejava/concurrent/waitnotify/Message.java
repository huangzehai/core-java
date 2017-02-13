package com.hzh.corejava.concurrent.waitnotify;

/**
 * Created by lenovo on 2017/2/13.
 */
public class Message {
    private String msg;

    public Message(String str) {
        this.msg = str;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

}
