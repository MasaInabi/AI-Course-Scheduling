package com.comp338.scheduler.comp338_coursescheduling;

import java.util.List;
import java.util.Random;

public class Selection {

    private static final Random random = new Random();

    public static Chromosome tournamentSelection(
            List<Chromosome> population,
            int tournamentSize) {

        Chromosome best = null;

        for (int i = 0; i < tournamentSize; i++) {

            int randomIndex =
                    random.nextInt(population.size());

            Chromosome candidate =
                    population.get(randomIndex);

            if (best == null ||
                    candidate.getFitness() > best.getFitness()) {

                best = candidate;
            }
        }

        return best;
    }

    public static Chromosome rouletteWheelSelection(
            List<Chromosome> population) {

        double totalFitness = 0.0;

        for (Chromosome chromosome : population) {
            totalFitness += chromosome.getFitness();
        }

        // Safety fallback
        if (totalFitness <= 0.0) {
            return population.get(
                    random.nextInt(population.size())
            );
        }

        double randomValue =
                random.nextDouble() * totalFitness;

        double cumulativeFitness = 0.0;

        for (Chromosome chromosome : population) {

            cumulativeFitness += chromosome.getFitness();

            if (cumulativeFitness >= randomValue) {
                return chromosome;
            }
        }

        return population.get(
                population.size() - 1
        );
    }
}