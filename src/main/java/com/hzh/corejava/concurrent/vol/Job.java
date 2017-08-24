package com.hzh.corejava.concurrent.vol;

public class Job {
    private boolean shutdownRequested;

    public void shutdown() {
        shutdownRequested = true;
    }

    public void run() {
        while (!shutdownRequested) {
            System.out.println("...");
        }
        System.out.println("Job is done");
    }

}
