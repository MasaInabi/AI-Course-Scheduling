package com.comp338.scheduler.comp338_coursescheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FitnessFunction {

    private static final int HARD_WEIGHT = 100;
    private static final int SOFT_WEIGHT = 10;

    public static void evaluate(
            Chromosome chromosome,
            DataManager dataManager) {

        int hardViolations = 0;
        int softViolations = 0;

        List<Gene> genes = chromosome.getGenes();



        for (int i = 0; i < genes.size(); i++) {

            Gene gene1 = genes.get(i);

            Course course1 = gene1.getCourse();
            Room room1 = gene1.getRoom();
            TimeSlot slot1 = gene1.getTimeSlot();

            if (course1.getNumberOfStudents() > room1.getCapacity()) {
                hardViolations++;
            }

            if (!course1.getType().equalsIgnoreCase(room1.getType())) {
                hardViolations++;
            }

            Instructor instructor =
                    findInstructor(
                            course1.getInstructorId(),
                            dataManager
                    );

            if (instructor != null &&
                    !instructor.isAvailable(slot1)) {

                hardViolations++;
            }

            for (int j = i + 1; j < genes.size(); j++) {

                Gene gene2 = genes.get(j);

                Course course2 = gene2.getCourse();
                Room room2 = gene2.getRoom();
                TimeSlot slot2 = gene2.getTimeSlot();

                boolean sameTime =
                        slot1.getDay().equals(slot2.getDay())
                                &&
                                slot1.getTime().equals(slot2.getTime());

                if (sameTime) {

                    // H4 - Instructor Conflict
                    if (course1.getInstructorId()
                            .equals(course2.getInstructorId())) {

                        hardViolations++;
                    }

                    if (room1.getId().equals(room2.getId())) {
                        hardViolations++;
                    }

                    if (sameStudentGroup(
                            course1.getId(),
                            course2.getId(),
                            dataManager)) {

                        hardViolations++;
                    }
                }
            }
        }



        for (Gene gene : genes) {

            TimeSlot slot = gene.getTimeSlot();

            if (slot.getTime().equals("08:00-09:00")) {
                softViolations++;
            }

            if (slot.getTime().equals("11:00-12:00")) {
                softViolations++;
            }
        }

        softViolations +=
                countStudentGaps(
                        chromosome,
                        dataManager
                );



        double penalty =
                HARD_WEIGHT * hardViolations
                        +
                        SOFT_WEIGHT * softViolations;

        double fitness =
                1.0 / (1.0 + penalty);

        chromosome.setHardViolations(hardViolations);
        chromosome.setSoftViolations(softViolations);
        chromosome.setPenalty(penalty);
        chromosome.setFitness(fitness);
    }

    private static Instructor findInstructor(
            String instructorId,
            DataManager dataManager) {

        for (Instructor instructor :
                dataManager.getInstructors()) {

            if (instructor.getId()
                    .equals(instructorId)) {

                return instructor;
            }
        }

        return null;
    }

    private static boolean sameStudentGroup(
            String courseId1,
            String courseId2,
            DataManager dataManager) {

        for (StudentGroup group :
                dataManager.getStudentGroups()) {

            if (group.hasCourse(courseId1)
                    &&
                    group.hasCourse(courseId2)) {

                return true;
            }
        }

        return false;
    }

    private static int countStudentGaps(
            Chromosome chromosome,
            DataManager dataManager) {

        int gaps = 0;

        String[] days = {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday"
        };

        for (StudentGroup group :
                dataManager.getStudentGroups()) {

            for (String day : days) {

                List<Integer> classTimes =
                        new ArrayList<>();

                for (Gene gene :
                        chromosome.getGenes()) {

                    if (group.hasCourse(
                            gene.getCourse().getId())
                            &&
                            gene.getTimeSlot()
                                    .getDay()
                                    .equals(day)) {

                        int index =
                                getTimeIndex(
                                        gene.getTimeSlot()
                                                .getTime()
                                );

                        if (index != -1) {
                            classTimes.add(index);
                        }
                    }
                }

                Collections.sort(classTimes);

                for (int i = 1;
                     i < classTimes.size();
                     i++) {

                    int difference =
                            classTimes.get(i)
                                    - classTimes.get(i - 1);

                    if (difference > 1) {
                        gaps += difference - 1;
                    }
                }
            }
        }

        return gaps;
    }

    private static int getTimeIndex(String time) {

        switch (time) {

            case "08:00-09:00":
                return 0;

            case "09:00-10:00":
                return 1;

            case "10:00-11:00":
                return 2;

            case "11:00-12:00":
                return 3;

            default:
                return -1;
        }
    }
}