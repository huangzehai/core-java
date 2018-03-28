package com.hzh.corejava.proxy;

public class SpeakerExample {
    public static void main(String[] args) {
        AiSpeaker speaker = (AiSpeaker) AuthenticationProxy.newInstance(new XiaoaiAiSpeeker());
        speaker.greeting();
    }
}
