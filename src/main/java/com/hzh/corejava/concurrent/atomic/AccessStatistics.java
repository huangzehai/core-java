package com.hzh.corejava.concurrent.atomic;

public class AccessStatistics {
    private final int noPages, noErrors;

    public AccessStatistics(int noPages, int noErrors) {
        this.noPages = noPages;
        this.noErrors = noErrors;
    }

    public int getNoPages() {
        return noPages;
    }

    public int getNoErrors() {
        return noErrors;
    }

    @Override
    public String toString() {
        return "AccessStatistics{" +
                "noPages=" + noPages +
                ", noErrors=" + noErrors +
                '}';
    }
}
