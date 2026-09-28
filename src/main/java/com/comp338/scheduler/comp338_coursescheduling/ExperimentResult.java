package com.comp338.scheduler.comp338_coursescheduling;

public class ExperimentResult {

    private String experiment;
    private String parameterValue;
    private double fitness;
    private int hardViolations;
    private int softViolations;
    private int generationsUsed;
    private double executionTime;

    public ExperimentResult(
            String experiment,
            String parameterValue,
            double fitness,
            int hardViolations,
            int softViolations,
            int generationsUsed,
            double executionTime) {

        this.experiment = experiment;
        this.parameterValue = parameterValue;
        this.fitness = fitness;
        this.hardViolations = hardViolations;
        this.softViolations = softViolations;
        this.generationsUsed = generationsUsed;
        this.executionTime = executionTime;
    }

    public String getExperiment() {
        return experiment;
    }

    public String getParameterValue() {
        return parameterValue;
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

    public int getGenerationsUsed() {
        return generationsUsed;
    }

    public double getExecutionTime() {
        return executionTime;
    }
}