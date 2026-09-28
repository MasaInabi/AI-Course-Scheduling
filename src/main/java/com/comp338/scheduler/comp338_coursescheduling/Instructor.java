package com.comp338.scheduler.comp338_coursescheduling;

import java.util.ArrayList;
import java.util.List;

public class Instructor {

    private String id;
    private String name;

    private List<TimeSlot> unavailableTimeSlots;

    public Instructor(String id, String name) {

        this.id = id;
        this.name = name;

        unavailableTimeSlots = new ArrayList<>();
    }

    public void addUnavailableTimeSlot(TimeSlot timeSlot) {
        unavailableTimeSlots.add(timeSlot);
    }

    public boolean isAvailable(TimeSlot timeSlot) {

        for (TimeSlot unavailable : unavailableTimeSlots) {

            if (unavailable.getDay().equals(timeSlot.getDay())
                    &&
                    unavailable.getTime().equals(timeSlot.getTime())) {

                return false;
            }
        }

        return true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TimeSlot> getUnavailableTimeSlots() {
        return unavailableTimeSlots;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}