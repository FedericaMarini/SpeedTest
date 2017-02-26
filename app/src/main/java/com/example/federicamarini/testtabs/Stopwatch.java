package com.example.federicamarini.testtabs;
public class Stopwatch {
    private long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    //Returns the elapsed time (in milliseconds) since the stopwatch was created
    public int elapsedTime() {
        long now = System.currentTimeMillis();
        return (int) (now - start);
    }

    //routine to schedule a task every 1 second
    public boolean OneSecond(){
        while (System.currentTimeMillis() - start < 1000){}
        return true;
    }

}

