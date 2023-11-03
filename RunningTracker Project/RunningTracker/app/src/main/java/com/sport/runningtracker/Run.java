package com.sport.runningtracker;

public class Run {

    private String distance;
    private String time;

    public Run(String distance, String time) {
        this.distance = distance;
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }
}
