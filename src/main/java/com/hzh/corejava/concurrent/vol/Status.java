package com.hzh.corejava.concurrent.vol;

public class Status {
    private volatile boolean shutdownRequested;

    public boolean isShutdownRequested() {
        return shutdownRequested;
    }

    public void setShutdownRequested(boolean shutdownRequested) {
        this.shutdownRequested = shutdownRequested;
    }
}
