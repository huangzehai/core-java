package com.hzh.corejava.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by lenovo on 2017/2/17.
 */
class BeeperControl {
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable beeper = () -> System.out.println("beep");
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 2, 2, SECONDS);
        scheduler.schedule(() -> {
            beeperHandle.cancel(true);
            scheduler.shutdown();
        }, 10, SECONDS);
    }

    public static void main(String[] args) {
        BeeperControl beeperControl = new BeeperControl();
        beeperControl.beepForAnHour();
    }
}