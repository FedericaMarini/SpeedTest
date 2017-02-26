package com.example.federicamarini.testtabs;

import static java.lang.Double.compare;

public class Operations {

    public double max(int[] array){
        double max;
        max = array[0];
        int i;
        for(i=1; i< array.length; i++){
            if(compare(array[i], max)>0){
                max = array[i];
            }
        }
        return max;
    }

    public double min(int[] array){
        double min;
        min = array[0];
        int i;
        for(i=1; i< array.length; i++){
            if(compare(array[i], min)<0){
                min = array[i];
            }
        }
        return min;
    }

    public double media(int[] array){
        int somma;
        double media;
        somma = 0;
        int i;
        for(i=0; i< array.length; i++){
            somma = somma + array[i];
        }
        media = somma/ array.length;
        return media;
    }

    public boolean controlloArray(int[] array){
        int cont = 0;
        for(int i=0; i<array.length; i++){
            if(array[i]==0) cont ++;
        }
        if(cont>3) return false;
        else return true;

    }
}