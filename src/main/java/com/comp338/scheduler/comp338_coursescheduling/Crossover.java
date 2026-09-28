package com.comp338.scheduler.comp338_coursescheduling;

import java.util.Random;

public class Crossover {

    private static final Random random = new Random();



    public static Chromosome onePointCrossover(
            Chromosome parent1,
            Chromosome parent2) {

        Chromosome child = new Chromosome();

        int size = parent1.getGenes().size();

        int crossoverPoint =
                random.nextInt(size);

        for (int i = 0; i < size; i++) {

            Gene selectedGene;

            if (i < crossoverPoint) {
                selectedGene =
                        parent1.getGenes().get(i);
            } else {
                selectedGene =
                        parent2.getGenes().get(i);
            }

            Gene newGene =
                    new Gene(
                            selectedGene.getCourse(),
                            selectedGene.getTimeSlot(),
                            selectedGene.getRoom()
                    );

            child.addGene(newGene);
        }

        return child;
    }




    public static Chromosome twoPointCrossover(
            Chromosome parent1,
            Chromosome parent2) {

        Chromosome child = new Chromosome();

        int size = parent1.getGenes().size();

        if (size < 2) {
            return onePointCrossover(
                    parent1,
                    parent2
            );
        }

        int point1 =
                random.nextInt(size);

        int point2 =
                random.nextInt(size);

        while (point2 == point1) {
            point2 = random.nextInt(size);
        }

        if (point1 > point2) {

            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        for (int i = 0; i < size; i++) {

            Gene selectedGene;

            if (i >= point1 && i < point2) {

                selectedGene =
                        parent2.getGenes().get(i);

            } else {

                selectedGene =
                        parent1.getGenes().get(i);
            }

            Gene newGene =
                    new Gene(
                            selectedGene.getCourse(),
                            selectedGene.getTimeSlot(),
                            selectedGene.getRoom()
                    );

            child.addGene(newGene);
        }

        return child;
    }
}