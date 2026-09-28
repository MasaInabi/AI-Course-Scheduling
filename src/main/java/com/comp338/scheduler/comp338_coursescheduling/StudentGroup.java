package com.comp338.scheduler.comp338_coursescheduling;

import java.util.ArrayList;
import java.util.List;

public class StudentGroup {

    private String id;
    private String name;
    private List<String> courseIds;

    public StudentGroup(String id, String name) {
        this.id = id;
        this.name = name;
        this.courseIds = new ArrayList<>();
    }

    public void addCourse(String courseId) {
        courseIds.add(courseId);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getCourseIds() {
        return courseIds;
    }

    public boolean hasCourse(String courseId) {
        return courseIds.contains(courseId);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
