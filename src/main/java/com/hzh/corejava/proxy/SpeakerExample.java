package com.hzh.corejava.proxy;

public class SpeakerExample {
    public static void main(String[] args) {
        AiSpeaker speaker = (AiSpeaker) AiSpeakerProxy.newInstance(new XiaoaiAiSpeeker());
        speaker.greeting();
    }
}
