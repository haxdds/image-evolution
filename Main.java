package com.company.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Lagrange on 3/20/2017.
 */
public class Main extends Canvas {
    public static int generation = 0;
    public static Population population;
    public static int population_size;
    public static boolean ending_conditions_met = false;
    public static int width;
    public static int height;
    public static int size;

    public static int[] refdata;
    public static GUI.ImageCanvas canvas;

    public static void main(String[] args) {

        population_size = 100;

        // the time it takes to process and the rate at which it learns goes up with
        // the size of the picture and the complexity of the picture

        //for some reason the population just stagnates after a while and progress becomes rare.



//          use this to grab pics off the internet.
//        String url = " ";
//        String filename = " ";
//
//        try {
//            Image.SaveImage(url,filename);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        BufferedImage refImage = null;
        try {
            refImage = ImageIO.read(new File("ap2.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Individual.height = refImage.getHeight();
        Individual.width = refImage.getWidth();
        Individual.size = Individual.width * Individual.height;

        size = refImage.getHeight() * refImage.getWidth();

        refdata = refImage.getRGB(0, 0, refImage.getWidth(), refImage.getHeight(), null, 0, refImage.getWidth());

        Individual in = new Individual(); //creating an individual so I can start up canvas and draw on it
        in.createIndividual();
        in.setImage();
        BufferedImage img = in.getImage();

        canvas = new GUI.ImageCanvas(img, refImage);

        new GUI(2 * refImage.getWidth(), 3 * refImage.getHeight(), "to", canvas);

        Genetic_Algorithm(refImage);


    }

    public static Population create_starting_population(BufferedImage referenceImage) {
        Population temp_population = new Population(population_size, true, referenceImage);

        generation = 1;

        return temp_population;
    }


    public static void Genetic_Algorithm(BufferedImage referenceImage) {


        population = create_starting_population(referenceImage);


        while (!ending_conditions_met) {


            System.out.println("Generation: " + generation + " Fittest: " + population.getFittest().getFitness());


            int[] lastGenFittest = population.getFittest().pixels;

            population = Algorithm.evolvePopulation(population);

            population.getFittest().setImage();
            canvas.setPic(population.getFittest().getImage());

            if (population.getFittest().pixels != lastGenFittest) {
                String s = Integer.toString(generation);
                try {
                    BufferedImage img1 = population.getFittest().getImage();
                    File outputfile = new File(s + ".png");
                    ImageIO.write(img1, "png", outputfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            generation++;


            if (generation == 1000000) ending_conditions_met = true;

        }
    }
}







