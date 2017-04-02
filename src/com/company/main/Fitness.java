package com.company.main;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Lagrange on 3/21/2017.
 */
public class Fitness {

    public static long calcFitness(int[] refdata, int[] pixeldata){
        long error = 0;
        long fitness;
        for(int k = 0; k < refdata.length; k++){

//            error += Math.abs(refdata[k] - pixeldata[k]);


           Color c1 = new Color(refdata[k]);
           Color c2 = new Color(pixeldata[k]);

           int errorRed = c1.getRed() - c2.getRed();
           int errorGreen = c1.getGreen() - c2.getGreen();
           int errorBlue = c1.getBlue() - c2.getBlue();

           error += Math.abs(errorRed + errorGreen + errorBlue);


        }


        fitness = -1 * error;
        return fitness;
    }


    public static byte[] toBinary(int i){
        return getByteByString(Integer.toBinaryString(i));
    }


    public static byte[] getByteByString(String a) {
        byte[] b = new byte[24];
        for (int i = 8; i< a.length(); i++) {
            b[i-8]= a.charAt(i)=='1' ? (byte)1 : (byte)0;
        }
        return b;
    }

    public static String toString(byte[] b){

        String binaryGene = "11111111";
        for(int k = 0 ; k < b.length; k++){
            String s = Byte.toString(b[k] );
            binaryGene = binaryGene + s;
        }

        return binaryGene;

    }

    public static int toInteger(byte[] b){
        return (int)Long.parseLong(toString(b),2);
    }



}
