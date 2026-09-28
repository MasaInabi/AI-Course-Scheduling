package com.comp338.scheduler.comp338_coursescheduling;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private List<Course> courses;
    private List<Room> rooms;
    private List<TimeSlot> timeSlots;
    private List<StudentGroup> studentGroups;
    private List<Instructor> instructors;

    private int numberOfCourses;

    public DataManager() {
        this(10);
    }

    public DataManager(int numberOfCourses) {

        this.numberOfCourses = numberOfCourses;

        courses = new ArrayList<>();
        rooms = new ArrayList<>();
        timeSlots = new ArrayList<>();
        studentGroups = new ArrayList<>();
        instructors = new ArrayList<>();

        loadCourses();
        loadRooms();
        loadTimeSlots();
        loadInstructors();
        loadStudentGroups();
    }



    private void loadCourses() {

        List<Course> baseCourses = new ArrayList<>();

        baseCourses.add(
                new Course(
                        "C01",
                        "AI",
                        35,
                        "I01",
                        "Lecture"
                )
        );

        baseCourses.add(
                new Course(
                        "C02",
                        "Database",
                        40,
                        "I02",
                        "Lecture"
                )
        );

        baseCourses.add(
                new Course(
                        "C03",
                        "Networks",
                        30,
                        "I03",
                        "Lecture"
                )
        );

        baseCourses.add(
                new Course(
                        "C04",
                        "Operating Systems",
                        35,
                        "I01",
                        "Lecture"
                )
        );

        baseCourses.add(
                new Course(
                        "C05",
                        "Software Engineering",
                        45,
                        "I04",
                        "Lecture"
                )
        );

        baseCourses.add(
                new Course(
                        "C06",
                        "Algorithms",
                        40,
                        "I05",
                        "Lecture"
                )
        );

        baseCourses.add(
                new Course(
                        "C07",
                        "Data Mining",
                        25,
                        "I06",
                        "Lecture"
                )
        );

        baseCourses.add(
                new Course(
                        "C08",
                        "Computer Graphics",
                        30,
                        "I07",
                        "Lab"
                )
        );

        baseCourses.add(
                new Course(
                        "C09",
                        "Web Programming",
                        25,
                        "I08",
                        "Lab"
                )
        );

        baseCourses.add(
                new Course(
                        "C10",
                        "Machine Learning",
                        35,
                        "I06",
                        "Lecture"
                )
        );

        // Add original courses first
        int baseCount =
                Math.min(
                        numberOfCourses,
                        baseCourses.size()
                );

        for (int i = 0; i < baseCount; i++) {
            courses.add(baseCourses.get(i));
        }

        // Generate extra synthetic courses
        for (int i = 11;
             i <= numberOfCourses;
             i++) {

            String id =
                    String.format(
                            "C%02d",
                            i
                    );

            String name =
                    "Generated Course " + i;

            String instructorId =
                    "I" +
                            String.format(
                                    "%02d",
                                    ((i - 1) % 8) + 1
                            );

            String type;

            if (i % 5 == 0) {
                type = "Lab";
            } else {
                type = "Lecture";
            }

            int students;

            if (type.equals("Lab")) {

                students =
                        20 + (i % 10);

            } else {

                students =
                        25 + (i % 20);
            }

            courses.add(
                    new Course(
                            id,
                            name,
                            students,
                            instructorId,
                            type
                    )
            );
        }
    }



    private void loadRooms() {

        rooms.add(
                new Room(
                        "R101",
                        50,
                        "Lecture"
                )
        );

        rooms.add(
                new Room(
                        "R102",
                        40,
                        "Lecture"
                )
        );

        rooms.add(
                new Room(
                        "R103",
                        35,
                        "Lecture"
                )
        );

        rooms.add(
                new Room(
                        "R104",
                        25,
                        "Lecture"
                )
        );

        rooms.add(
                new Room(
                        "LAB1",
                        30,
                        "Lab"
                )
        );

        rooms.add(
                new Room(
                        "LAB2",
                        25,
                        "Lab"
                )
        );
    }



    private void loadTimeSlots() {

        String[] days = {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday"
        };

        String[] times = {
                "08:00-09:00",
                "09:00-10:00",
                "10:00-11:00",
                "11:00-12:00"
        };

        for (String day : days) {

            for (String time : times) {

                timeSlots.add(
                        new TimeSlot(
                                day,
                                time
                        )
                );
            }
        }
    }



    private void loadInstructors() {

        Instructor i01 =
                new Instructor(
                        "I01",
                        "Instructor 1"
                );

        Instructor i02 =
                new Instructor(
                        "I02",
                        "Instructor 2"
                );

        Instructor i03 =
                new Instructor(
                        "I03",
                        "Instructor 3"
                );

        Instructor i04 =
                new Instructor(
                        "I04",
                        "Instructor 4"
                );

        Instructor i05 =
                new Instructor(
                        "I05",
                        "Instructor 5"
                );

        Instructor i06 =
                new Instructor(
                        "I06",
                        "Instructor 6"
                );

        Instructor i07 =
                new Instructor(
                        "I07",
                        "Instructor 7"
                );

        Instructor i08 =
                new Instructor(
                        "I08",
                        "Instructor 8"
                );


        i01.addUnavailableTimeSlot(
                new TimeSlot(
                        "Sunday",
                        "08:00-09:00"
                )
        );

        i02.addUnavailableTimeSlot(
                new TimeSlot(
                        "Monday",
                        "11:00-12:00"
                )
        );

        i03.addUnavailableTimeSlot(
                new TimeSlot(
                        "Tuesday",
                        "08:00-09:00"
                )
        );

        i04.addUnavailableTimeSlot(
                new TimeSlot(
                        "Wednesday",
                        "11:00-12:00"
                )
        );

        i05.addUnavailableTimeSlot(
                new TimeSlot(
                        "Thursday",
                        "08:00-09:00"
                )
        );

        i06.addUnavailableTimeSlot(
                new TimeSlot(
                        "Sunday",
                        "11:00-12:00"
                )
        );

        i07.addUnavailableTimeSlot(
                new TimeSlot(
                        "Monday",
                        "08:00-09:00"
                )
        );

        i08.addUnavailableTimeSlot(
                new TimeSlot(
                        "Thursday",
                        "11:00-12:00"
                )
        );


        instructors.add(i01);
        instructors.add(i02);
        instructors.add(i03);
        instructors.add(i04);
        instructors.add(i05);
        instructors.add(i06);
        instructors.add(i07);
        instructors.add(i08);
    }



    private void loadStudentGroups() {

        StudentGroup g1 =
                new StudentGroup(
                        "G1",
                        "CS Group 1"
                );

        StudentGroup g2 =
                new StudentGroup(
                        "G2",
                        "CS Group 2"
                );

        StudentGroup g3 =
                new StudentGroup(
                        "G3",
                        "CS Group 3"
                );


        for (int i = 0;
             i < courses.size();
             i++) {

            String courseId =
                    courses.get(i).getId();

            if (i % 3 == 0) {

                g1.addCourse(courseId);

            } else if (i % 3 == 1) {

                g2.addCourse(courseId);

            } else {

                g3.addCourse(courseId);
            }
        }

        studentGroups.add(g1);
        studentGroups.add(g2);
        studentGroups.add(g3);
    }



    public List<Course> getCourses() {
        return courses;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }
}