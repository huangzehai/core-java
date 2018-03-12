package com.hzh.corejava.concurrent.atom;

import java.util.concurrent.atomic.AtomicReference;

public class AccessStatisticsProcessor {
    private AtomicReference<AccessStatistics> stats =
            new AtomicReference<AccessStatistics>(new AccessStatistics(0, 0));

    public AtomicReference<AccessStatistics> getStats() {
        return stats;
    }

    public void incrementPageCount(boolean wasError) {
        AccessStatistics prev, newValue;
        do {
            prev = stats.get();
            int noPages = prev.getNoPages() + 1;
            int noErrors = prev.getNoErrors();
            if (wasError) {
                noErrors++;
            }
            newValue = new AccessStatistics(noPages, noErrors);
        } while (!stats.compareAndSet(prev, newValue));
    }
}
