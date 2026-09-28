package com.comp338.scheduler.comp338_coursescheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GeneticAlgorithm {

    private int populationSize;
    private int generations;
    private double mutationRate;
    private double crossoverRate;
    private int tournamentSize;

    private DataManager dataManager;

    private List<Double> fitnessHistory;

    private int generationsUsed;

    // Selection method:
    // TOURNAMENT or ROULETTE
    private String selectionMethod;

    // Crossover method:
    // ONE_POINT or TWO_POINT
    private String crossoverMethod;

    // Mutation method:
    // RANDOM or SWAP
    private String mutationMethod;


    // ---------------------------------------------------------
    // OLD CONSTRUCTOR
    // Keeps all existing code working exactly as before.
    // Defaults:
    // Tournament Selection
    // One-Point Crossover
    // Random Mutation
    // ---------------------------------------------------------
    public GeneticAlgorithm(
            int populationSize,
            int generations,
            double mutationRate,
            double crossoverRate,
            int tournamentSize,
            DataManager dataManager) {

        this(
                populationSize,
                generations,
                mutationRate,
                crossoverRate,
                tournamentSize,
                dataManager,
                "TOURNAMENT",
                "ONE_POINT",
                "RANDOM"
        );
    }


    // ---------------------------------------------------------
    // CONSTRUCTOR FOR SELECTION EXPERIMENT
    // Crossover and Mutation remain defaults.
    // ---------------------------------------------------------
    public GeneticAlgorithm(
            int populationSize,
            int generations,
            double mutationRate,
            double crossoverRate,
            int tournamentSize,
            DataManager dataManager,
            String selectionMethod) {

        this(
                populationSize,
                generations,
                mutationRate,
                crossoverRate,
                tournamentSize,
                dataManager,
                selectionMethod,
                "ONE_POINT",
                "RANDOM"
        );
    }


    // ---------------------------------------------------------
    // CONSTRUCTOR FOR CROSSOVER EXPERIMENT
    // Mutation remains Random by default.
    // ---------------------------------------------------------
    public GeneticAlgorithm(
            int populationSize,
            int generations,
            double mutationRate,
            double crossoverRate,
            int tournamentSize,
            DataManager dataManager,
            String selectionMethod,
            String crossoverMethod) {

        this(
                populationSize,
                generations,
                mutationRate,
                crossoverRate,
                tournamentSize,
                dataManager,
                selectionMethod,
                crossoverMethod,
                "RANDOM"
        );
    }


    // ---------------------------------------------------------
    // FULL CONSTRUCTOR
    // Allows choosing:
    // Selection Method
    // Crossover Method
    // Mutation Method
    // ---------------------------------------------------------
    public GeneticAlgorithm(
            int populationSize,
            int generations,
            double mutationRate,
            double crossoverRate,
            int tournamentSize,
            DataManager dataManager,
            String selectionMethod,
            String crossoverMethod,
            String mutationMethod) {

        this.populationSize = populationSize;
        this.generations = generations;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.tournamentSize = tournamentSize;
        this.dataManager = dataManager;

        this.selectionMethod = selectionMethod;
        this.crossoverMethod = crossoverMethod;
        this.mutationMethod = mutationMethod;

        this.fitnessHistory = new ArrayList<>();
        this.generationsUsed = 0;
    }


    public Chromosome run() {

        fitnessHistory.clear();
        generationsUsed = 0;

        Population population =
                new Population(
                        populationSize,
                        dataManager
                );

        evaluatePopulation(
                population.getChromosomes()
        );

        for (int generation = 0;
             generation < generations;
             generation++) {

            List<Chromosome> newPopulation =
                    new ArrayList<>();

            // Elitism:
            // preserve the best chromosome
            Chromosome best =
                    getBestChromosome(
                            population.getChromosomes()
                    );

            newPopulation.add(
                    copyChromosome(best)
            );

            while (newPopulation.size()
                    < populationSize) {

                Chromosome parent1 =
                        selectParent(
                                population.getChromosomes()
                        );

                Chromosome parent2 =
                        selectParent(
                                population.getChromosomes()
                        );

                Chromosome child;

                if (Math.random() < crossoverRate) {

                    child =
                            applyCrossover(
                                    parent1,
                                    parent2
                            );

                } else {

                    child =
                            copyChromosome(parent1);
                }

                applyMutation(child);

                FitnessFunction.evaluate(
                        child,
                        dataManager
                );

                newPopulation.add(child);
            }

            population.getChromosomes().clear();

            population.getChromosomes()
                    .addAll(newPopulation);

            Chromosome generationBest =
                    getBestChromosome(
                            population.getChromosomes()
                    );

            fitnessHistory.add(
                    generationBest.getFitness()
            );

            generationsUsed =
                    generation + 1;

            System.out.println(
                    "Generation "
                            + generation
                            + " | Selection = "
                            + selectionMethod
                            + " | Crossover = "
                            + crossoverMethod
                            + " | Mutation = "
                            + mutationMethod
                            + " | Fitness = "
                            + generationBest.getFitness()
                            + " | Hard = "
                            + generationBest.getHardViolations()
                            + " | Soft = "
                            + generationBest.getSoftViolations()
            );

            if (generationBest.getFitness() == 1.0) {

                System.out.println(
                        "Optimal timetable found at generation "
                                + generationsUsed
                );

                break;
            }
        }

        return getBestChromosome(
                population.getChromosomes()
        );
    }



    private Chromosome selectParent(
            List<Chromosome> population) {

        if ("ROULETTE".equalsIgnoreCase(selectionMethod)) {

            return Selection.rouletteWheelSelection(
                    population
            );
        }

        return Selection.tournamentSelection(
                population,
                tournamentSize
        );
    }



    private Chromosome applyCrossover(
            Chromosome parent1,
            Chromosome parent2) {

        if ("TWO_POINT".equalsIgnoreCase(crossoverMethod)) {

            return Crossover.twoPointCrossover(
                    parent1,
                    parent2
            );
        }

        return Crossover.onePointCrossover(
                parent1,
                parent2
        );
    }



    private void applyMutation(
            Chromosome chromosome) {

        if ("SWAP".equalsIgnoreCase(mutationMethod)) {

            Mutation.swapMutation(
                    chromosome,
                    mutationRate
            );

        } else {

            // Default = Random Mutation
            Mutation.mutate(
                    chromosome,
                    mutationRate,
                    dataManager
            );
        }
    }


    private void evaluatePopulation(
            List<Chromosome> chromosomes) {

        for (Chromosome chromosome :
                chromosomes) {

            FitnessFunction.evaluate(
                    chromosome,
                    dataManager
            );
        }
    }


    private Chromosome getBestChromosome(
            List<Chromosome> chromosomes) {

        return chromosomes
                .stream()
                .max(
                        Comparator.comparingDouble(
                                Chromosome::getFitness
                        )
                )
                .orElse(null);
    }


    private Chromosome copyChromosome(
            Chromosome original) {

        Chromosome copy =
                new Chromosome();

        for (Gene gene :
                original.getGenes()) {

            Gene newGene =
                    new Gene(
                            gene.getCourse(),
                            gene.getTimeSlot(),
                            gene.getRoom()
                    );

            copy.addGene(newGene);
        }

        copy.setHardViolations(
                original.getHardViolations()
        );

        copy.setSoftViolations(
                original.getSoftViolations()
        );

        copy.setPenalty(
                original.getPenalty()
        );

        copy.setFitness(
                original.getFitness()
        );

        return copy;
    }


    public List<Double> getFitnessHistory() {
        return fitnessHistory;
    }


    public int getGenerationsUsed() {
        return generationsUsed;
    }


    public String getSelectionMethod() {
        return selectionMethod;
    }


    public String getCrossoverMethod() {
        return crossoverMethod;
    }


    public String getMutationMethod() {
        return mutationMethod;
    }
}