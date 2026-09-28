package com.comp338.scheduler.comp338_coursescheduling;

public class Course {
    private String id;
    private String name;
    private int numberOfStudents;
    private String instructorId;
    private String type;

    public Course(String id, String name, int numberOfStudents,
                  String instructorId, String type) {

        this.id = id;
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        this.instructorId = instructorId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
