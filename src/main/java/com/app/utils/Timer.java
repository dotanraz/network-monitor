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

    public long currentTimeDiffSec() {
        incrementDiffRequestCounter();
        return (System.currentTimeMillis() - this.startTime)/1000;
    }

    public void startTimer() {
        resetStartTime();
    }
    public void resetStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void resetDiffRequestCounter() {
        this.diffRequestCounter = 0;
    }

    public void incrementDiffRequestCounter() {
        this.diffRequestCounter +=1;
        if (this.diffRequestCounter > 999999) { //avoid reaching max int value
            this.diffRequestCounter = 1;
        }
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
