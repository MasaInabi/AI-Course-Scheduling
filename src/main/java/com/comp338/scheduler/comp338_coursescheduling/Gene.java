package com.comp338.scheduler.comp338_coursescheduling;

public class Gene {

    private Course course;
    private TimeSlot timeSlot;
    private Room room;

    public Gene(Course course, TimeSlot timeSlot, Room room) {
        this.course = course;
        this.timeSlot = timeSlot;
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Room getRoom() {
        return room;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return course.getName() + " -> "
                + timeSlot.getDay() + " "
                + timeSlot.getTime() + " | "
                + room.getId();
    }
}