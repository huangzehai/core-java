package com.hzh.corejava.proxy;

public class XiaoaiAiSpeeker implements AiSpeaker {
    @Override
    public String greeting() {
        System.out.println("Welcome, I'm Xiao Ai");
        return "Welcome";
    }
}
