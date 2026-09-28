package com.comp338.scheduler.comp338_coursescheduling;

public class Room {

    private String id;
    private int capacity;
    private String type;

    public Room(String id, int capacity, String type) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + " (" + type + ", Capacity: " + capacity + ")";
    }
}