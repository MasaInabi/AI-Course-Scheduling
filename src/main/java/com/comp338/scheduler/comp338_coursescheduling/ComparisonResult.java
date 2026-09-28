package com.comp338.scheduler.comp338_coursescheduling;

public class ComparisonResult {

    private String algorithm;
    private double fitness;
    private int hardViolations;
    private int softViolations;
    private double executionTime;

    public ComparisonResult(
            String algorithm,
            double fitness,
            int hardViolations,
            int softViolations,
            double executionTime) {

        this.algorithm = algorithm;
        this.fitness = fitness;
        this.hardViolations = hardViolations;
        this.softViolations = softViolations;
        this.executionTime = executionTime;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public double getFitness() {
        return fitness;
    }

    public int getHardViolations() {
        return hardViolations;
    }

    public int getSoftViolations() {
        return softViolations;
    }

    public double getExecutionTime() {
        return executionTime;
    }
}