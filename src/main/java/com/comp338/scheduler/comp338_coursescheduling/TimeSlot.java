package com.comp338.scheduler.comp338_coursescheduling;

public class TimeSlot {

    private String day;
    private String time;

    public TimeSlot(String day, String time) {
        this.day = day;
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return day + " " + time;
    }
}

