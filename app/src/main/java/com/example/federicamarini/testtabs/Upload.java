package com.example.federicamarini.testtabs;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Upload {
    private String linkUploadTest;
    private String uriFile;
    private int intervalUploadTest;
    private int destinationPort;
    private int[] resultsUpload;
    private Operations operations;

    public Upload() {
        this.linkUploadTest = "http://193.104.137.133"; //server test: 193.104.137.133
        this.uriFile = "///storage/emulated/0/prova/ksnn_compilation_master_the_internet_512kb.mp4";
        this.intervalUploadTest = 10;
        this.destinationPort = 80;
        this.resultsUpload = new int [intervalUploadTest];
    }

    public void RunUploadTest () { //String linkUploadTest, int destinationPort, String uriFile

        try {
            URL url = new URL(linkUploadTest);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //parameters
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "video/mp4");

            for(int i =0; i<intervalUploadTest; i++) {
                try {
                    FileInputStream fis = new FileInputStream(uriFile);
                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                    int bytesWrite = 0;
                    byte[] buffer = new byte[512];
                    Stopwatch timer = new Stopwatch();
                    int fRead;
                    while ((fRead = fis.read()) != -1 && timer.elapsedTime() < 1000) {
                        dos.write(buffer);
                        dos.flush();
                        bytesWrite++;
                    }
                    Log.d("Upload", "Byte scritti: " + bytesWrite);
                    resultsUpload[i] = bytesWrite;

            } catch (IOException e){
                    e.printStackTrace();
                    resultsUpload[i] = -1;
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public int[] getResultsUpload() {
        return resultsUpload;
    }

    public double getMedia(){
        return operations.media(resultsUpload);
    }
}