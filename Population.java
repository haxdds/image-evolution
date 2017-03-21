package com.company.main;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Lagrange on 3/21/2017.
 */
public class Population {
    Individual[] individuals;
    BufferedImage refImage;
    int[] refPixels;

    public Population(int pop_size, boolean initialize, BufferedImage referenceImage){

        individuals = new Individual[pop_size];
        refImage = referenceImage;

        refPixels = refImage.getRGB(0,0, refImage.getWidth(), refImage.getHeight(), null, 0, refImage.getWidth());

        if(initialize){
            for(int k = 0; k < pop_size; k++){
                Individual indiv = new Individual();
                indiv.createIndividual();
                individuals[k] = indiv;
            }
        }
    }

    public BufferedImage getRefImage(){
        return refImage;
    }

    public int[] getRefPixels(){
        return refPixels;
    }


    public Individual getIndividual(int index){
        return individuals[index];
    }

    public void saveIndividual(int index,Individual indiv){
        individuals[index] = indiv;
    }

    public int size(){
        return individuals.length;
    }



    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }





}
