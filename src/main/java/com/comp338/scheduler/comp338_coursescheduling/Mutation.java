package com.comp338.scheduler.comp338_coursescheduling;

import java.util.List;
import java.util.Random;

public class Mutation {

    private static final Random random = new Random();
    public static void mutate(
            Chromosome chromosome,
            double mutationRate,
            DataManager dataManager) {

        List<Gene> genes = chromosome.getGenes();
        List<Room> rooms = dataManager.getRooms();
        List<TimeSlot> timeSlots = dataManager.getTimeSlots();

        for (Gene gene : genes) {

            double chance = random.nextDouble();

            if (chance < mutationRate) {

                if (random.nextBoolean()) {

                    TimeSlot newTimeSlot =
                            timeSlots.get(
                                    random.nextInt(timeSlots.size())
                            );

                    gene.setTimeSlot(newTimeSlot);

                } else {

                    Room newRoom =
                            rooms.get(
                                    random.nextInt(rooms.size())
                            );

                    gene.setRoom(newRoom);
                }
            }
        }
    }


    public static void swapMutation(
            Chromosome chromosome,
            double mutationRate) {

        List<Gene> genes =
                chromosome.getGenes();

        if (genes.size() < 2) {
            return;
        }

        if (random.nextDouble() < mutationRate) {

            int index1 =
                    random.nextInt(
                            genes.size()
                    );

            int index2 =
                    random.nextInt(
                            genes.size()
                    );

            while (index2 == index1) {

                index2 =
                        random.nextInt(
                                genes.size()
                        );
            }

            Gene gene1 =
                    genes.get(index1);

            Gene gene2 =
                    genes.get(index2);

            TimeSlot tempTimeSlot =
                    gene1.getTimeSlot();

            Room tempRoom =
                    gene1.getRoom();

            gene1.setTimeSlot(
                    gene2.getTimeSlot()
            );

            gene1.setRoom(
                    gene2.getRoom()
            );

            gene2.setTimeSlot(
                    tempTimeSlot
            );

            gene2.setRoom(
                    tempRoom
            );
        }
    }
}