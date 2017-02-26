package com.example.federicamarini.testtabs;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SaveCSV {
    private File file;
    private RandomAccessFile randomAccessFile;


    public SaveCSV(){
        this.file = getPath("RisultatiTest.csv");
        try {
            this.randomAccessFile = new RandomAccessFile(file, "rwd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void salvaRisultati (int[] array) {
        try {
            file.createNewFile();
            String s = "";
            for (int i = 0; i < array.length; i++) {
                s = s + array[i] + ",";
            }
            randomAccessFile.writeBytes(s);
            randomAccessFile.writeBytes("\n");
        } catch (IOException e) {
            throw new RuntimeException("Unable to create File " + e);
        }
    }

    public static File getPath(String fileName){
        return new File(Environment.getExternalStorageDirectory(), fileName);

    }
}