package com.hzh.corejava.concurrent.readwritelock;

/**
 * Created by lenovo on 2017/2/16.
 */

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ScoreBoard {
    private int score = 0;
    String health = "Not Available";
    final ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

    public String getMatchHealth() {
        rrwl.readLock().lock();
        try {
            if (score % 2 == 0) {
                health = "Bad Score";
            } else {
                health = "Good Score";
            }
        } finally {
            rrwl.readLock().unlock();
        }
        return health;
    }

    public int getScore() {
        rrwl.readLock().lock();
        int sc = 0;
        try {
            sc = score;
            System.out.println(new Date() + " " + Thread.currentThread().getId() + "# Score: " + sc);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rrwl.readLock().unlock();
        }
        return sc;
    }

    public void updateScore() {
        try {
            rrwl.writeLock().lock();
            //perform more task here
            System.out.println(new Date() + " Score Updated.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            score++;
        } finally {
            rrwl.writeLock().unlock();
        }
    }

}