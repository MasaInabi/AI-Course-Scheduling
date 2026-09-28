package com.comp338.scheduler.comp338_coursescheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

    private List<Chromosome> chromosomes;

    public Population(int populationSize, DataManager dataManager) {

        chromosomes = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            chromosomes.add(createRandomChromosome(dataManager));
        }
    }

    private Chromosome createRandomChromosome(DataManager dataManager) {

        Chromosome chromosome = new Chromosome();

        Random random = new Random();

        List<Course> courses = dataManager.getCourses();
        List<Room> rooms = dataManager.getRooms();
        List<TimeSlot> timeSlots = dataManager.getTimeSlots();

        for (Course course : courses) {

            Room randomRoom =
                    rooms.get(random.nextInt(rooms.size()));

            TimeSlot randomTimeSlot =
                    timeSlots.get(random.nextInt(timeSlots.size()));

            Gene gene =
                    new Gene(course, randomTimeSlot, randomRoom);

            chromosome.addGene(gene);
        }

        return chromosome;
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }
}