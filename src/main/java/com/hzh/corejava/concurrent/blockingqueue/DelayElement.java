package com.hzh.corejava.concurrent.blockingqueue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangzehai on 2017/4/5.
 */
public class DelayElement implements Delayed {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private String element;
    private long expiryTime;

    public DelayElement(String element, long delay) {
        this.element = element;
        this.expiryTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit timeUnit) {
        long diff = expiryTime - System.currentTimeMillis();
        return timeUnit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.expiryTime < ((DelayElement) o).expiryTime) {
            return -1;
        }
        if (this.expiryTime > ((DelayElement) o).expiryTime) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(expiryTime);
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return "DelayElement{" +
                "element='" + element + '\'' +
                ", expiryTime=" + df.format(calendar.getTime()) +
                '}';
    }
}