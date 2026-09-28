package com.comp338.scheduler.comp338_coursescheduling;

import java.util.ArrayList;
import java.util.List;

public class Chromosome {

    private List<Gene> genes;

    private int hardViolations;
    private int softViolations;

    private double penalty;
    private double fitness;

    public Chromosome() {
        genes = new ArrayList<>();
        hardViolations = 0;
        softViolations = 0;
        penalty = 0;
        fitness = 0;
    }

    public void addGene(Gene gene) {
        genes.add(gene);
    }

    public List<Gene> getGenes() {
        return genes;
    }

    public int getHardViolations() {
        return hardViolations;
    }

    public void setHardViolations(int hardViolations) {
        this.hardViolations = hardViolations;
    }

    public int getSoftViolations() {
        return softViolations;
    }

    public void setSoftViolations(int softViolations) {
        this.softViolations = softViolations;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for (Gene gene : genes) {
            result.append(gene).append("\n");
        }

        result.append("Hard Violations: ")
                .append(hardViolations)
                .append("\n");

        result.append("Soft Violations: ")
                .append(softViolations)
                .append("\n");

        result.append("Penalty: ")
                .append(penalty)
                .append("\n");

        result.append("Fitness: ")
                .append(fitness);

        return result.toString();
    }
}