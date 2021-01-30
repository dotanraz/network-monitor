package com.app.utils;

public class Timer {

    long startTime;
    int diffRequestCounter = 0;

    public Timer() {
    }

    public long currentTimeDiffMin() {
        incrementDiffRequestCounter();
        return (System.currentTimeMillis() - this.startTime)/60000;
    }

    public void resetStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    private void incrementDiffRequestCounter() {
        this.diffRequestCounter +=1;
    }

    public long getStart() {
        return startTime;
    }

    public void setStart(long startTime) {
        this.startTime = startTime;
    }

    public int getDiffRequestCounter() {
        return diffRequestCounter;
    }

    public void setDiffRequestCounter(int diffRequestCounter) {
        this.diffRequestCounter = diffRequestCounter;
    }
}
