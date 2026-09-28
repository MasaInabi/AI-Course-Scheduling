package com.comp338.scheduler.comp338_coursescheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HillClimbing {

    private DataManager dataManager;
    private int maxIterations;

    private Random random;

    private List<Double> fitnessHistory;

    private int iterationsUsed;


    public HillClimbing(
            DataManager dataManager,
            int maxIterations) {

        this.dataManager = dataManager;
        this.maxIterations = maxIterations;
        this.random = new Random();

        this.fitnessHistory =
                new ArrayList<>();

        this.iterationsUsed = 0;
    }


    public Chromosome run() {

        fitnessHistory.clear();
        iterationsUsed = 0;

        Chromosome currentSolution =
                createRandomSolution();

        FitnessFunction.evaluate(
                currentSolution,
                dataManager
        );

        fitnessHistory.add(
                currentSolution.getFitness()
        );

        System.out.println(
                "=== HILL CLIMBING STARTED ==="
        );

        System.out.println(
                "Initial Solution"
                        + " | Fitness = "
                        + currentSolution.getFitness()
                        + " | Hard = "
                        + currentSolution.getHardViolations()
                        + " | Soft = "
                        + currentSolution.getSoftViolations()
        );


        if (currentSolution.getFitness() == 1.0) {

            System.out.println(
                    "Optimal solution found before iterations started."
            );

            return currentSolution;
        }


        for (int iteration = 0;
             iteration < maxIterations;
             iteration++) {

            Chromosome neighbor =
                    createNeighbor(
                            currentSolution
                    );

            FitnessFunction.evaluate(
                    neighbor,
                    dataManager
            );


            if (neighbor.getFitness()
                    > currentSolution.getFitness()) {

                currentSolution = neighbor;
            }


            fitnessHistory.add(
                    currentSolution.getFitness()
            );

            iterationsUsed =
                    iteration + 1;


            System.out.println(
                    "Hill Climbing Iteration "
                            + iteration
                            + " | Fitness = "
                            + currentSolution.getFitness()
                            + " | Hard = "
                            + currentSolution.getHardViolations()
                            + " | Soft = "
                            + currentSolution.getSoftViolations()
            );


            if (currentSolution.getFitness()
                    == 1.0) {

                System.out.println(
                        "Optimal solution found at iteration "
                                + iterationsUsed
                );

                break;
            }
        }


        return currentSolution;
    }


    private Chromosome createRandomSolution() {

        Chromosome chromosome =
                new Chromosome();

        List<Course> courses =
                dataManager.getCourses();

        List<Room> rooms =
                dataManager.getRooms();

        List<TimeSlot> timeSlots =
                dataManager.getTimeSlots();


        for (Course course : courses) {

            Room randomRoom =
                    rooms.get(
                            random.nextInt(
                                    rooms.size()
                            )
                    );

            TimeSlot randomTimeSlot =
                    timeSlots.get(
                            random.nextInt(
                                    timeSlots.size()
                            )
                    );


            chromosome.addGene(
                    new Gene(
                            course,
                            randomTimeSlot,
                            randomRoom
                    )
            );
        }


        return chromosome;
    }



    private Chromosome createNeighbor(
            Chromosome original) {

        Chromosome neighbor =
                copyChromosome(
                        original
                );


        int randomGeneIndex =
                random.nextInt(
                        neighbor
                                .getGenes()
                                .size()
                );


        Gene gene =
                neighbor
                        .getGenes()
                        .get(
                                randomGeneIndex
                        );


        // Randomly modify either time or room
        if (random.nextBoolean()) {

            List<TimeSlot> timeSlots =
                    dataManager
                            .getTimeSlots();


            TimeSlot newTime =
                    timeSlots.get(
                            random.nextInt(
                                    timeSlots.size()
                            )
                    );


            gene.setTimeSlot(
                    newTime
            );

        } else {

            List<Room> rooms =
                    dataManager
                            .getRooms();


            Room newRoom =
                    rooms.get(
                            random.nextInt(
                                    rooms.size()
                            )
                    );


            gene.setRoom(
                    newRoom
            );
        }


        return neighbor;
    }



    private Chromosome copyChromosome(
            Chromosome original) {

        Chromosome copy =
                new Chromosome();


        for (Gene gene :
                original.getGenes()) {

            copy.addGene(
                    new Gene(
                            gene.getCourse(),
                            gene.getTimeSlot(),
                            gene.getRoom()
                    )
            );
        }


        return copy;
    }



    public List<Double> getFitnessHistory() {

        return fitnessHistory;
    }


    public int getIterationsUsed() {

        return iterationsUsed;
    }
}