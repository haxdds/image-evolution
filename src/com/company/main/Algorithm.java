package com.company.main;

/**
 * Created by Lagrange on 3/21/2017.
 */
public class Algorithm {
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = false;

    /* Public methods */

    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false, pop.getRefImage());

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }


        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);

            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    public static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();

        // Loop through genes
        for(int k = 0; k < indiv1.pixels.length; k++) {

            byte[] b = new byte[indiv1.gene_length];
            for (int i = 0; i < indiv1.gene_length; i++){

                if (Math.random() <= uniformRate) {
                    b[i] = indiv1.getGene(k,i);
                } else {
                    b[i] = indiv2.getGene(k,i);
                }
            }

            newSol.chromosome.put(k,b);


        }
        newSol.fillPixel(newSol.chromosome);
        return newSol;
    }

    // Mutate an individual
    public static void mutate(Individual indiv) {
        // Loop through genes
        for(int k = 0; k < indiv.pixels.length; k++) {

            for (int i = 0; i < indiv.gene_length; i++) {
                if (Math.random() <= mutationRate) {
                    // Create random gene
                    byte gene = (byte) Math.round(Math.random());
                    indiv.chromosome.get(k)[i] = gene;
                }
            }

        }
    }

    // Select individuals for crossover
    public static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false,pop.refImage);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }

}
