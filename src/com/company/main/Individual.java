package com.company.main;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;

/**
 * Created by Lagrange on 3/21/2017.
 */
public class Individual {

    //an individual is a entire pixel array and a population is a bunch of pixel arrays put together


    static int gene_length = 24;


    public int[] pixels = new int[Main.size];
    public HashMap<Integer, byte[]> chromosome = new HashMap<>();

    public static int width ;
    public static int height ;
    public static int size = width * height;

    public long fitness = 0;

    public BufferedImage image;
    public BufferedImage refImage;
    int[] refPixels = Main.refdata;

    public void createIndividual(){






        for(int i = 0; i < size; i++) {
            String gene = "11111111";
            String rgbGene = "";
            for (int k = 0; k < gene_length; k++) {
                String tempgene = Long.toString(Math.round(Math.random()));
                gene = gene + tempgene;
                rgbGene = rgbGene + tempgene;
            }
            int rgb = (int) Long.parseLong(gene, 2);
            int rgb2 = (int) Long.parseLong(rgbGene, 2);
            pixels[i] = rgb;
            chromosome.put(i,Fitness.toBinary(rgb2));
        }

        setImage();

    }

    public void setImage(){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, width, height, pixels, 0, width);
    }

    public BufferedImage getImage(){

        return image;
    }

    public BufferedImage getRefImage(){
        return refImage;
    }
    public int getPixel(int index){
        return pixels[index];
    }

    public void setPixel(int index,byte[] gene){
        pixels[index] = Fitness.toInteger(gene);
    }


    public long getFitness(){
        return Fitness.calcFitness(refPixels,pixels);
    }

    public int[] getRefPixels(){
        return refPixels;
    }

    public byte getGene(int key, int index){
        return chromosome.get(key)[index];
    }

    public void setGene(int key, byte[] gene){
        chromosome.put(key,gene);
    }


    public void fillPixel(HashMap<Integer, byte[]> h){
        for(int k = 0; k < pixels.length; k++){
            pixels[k] = Fitness.toInteger(h.get(k));
        }
    }








}
