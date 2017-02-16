package com.hzh.corejava.concurrent.readwritelock;

import java.util.Date;

/**
 * Created by lenovo on 2017/2/16.
 */
public class ScoreUpdateThread implements Runnable {
    private ScoreBoard scoreBoard;

    public ScoreUpdateThread(ScoreBoard scoreTable) {
        this.scoreBoard = scoreTable;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            scoreBoard.updateScore();
        }
    }
}