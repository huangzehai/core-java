package com.hzh.corejava.concurrent.readwritelock;

/**
 * Created by lenovo on 2017/2/16.
 */
public class ScoreHealthThread implements Runnable {
    private ScoreBoard scoreBoard;

    public ScoreHealthThread(ScoreBoard scoreTable) {
        this.scoreBoard = scoreTable;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            scoreBoard.getScore();
        }
    }
}