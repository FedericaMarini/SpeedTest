package com.example.federicamarini.testtabs;

import android.os.Bundle;
import android.util.Log;
import java.net.Socket;

public class Ping {
    private String linkPingTest;
    private int portPingTest;
    private int intervalPingTest;
    private int[] resultsPing;
    private Operations operations;

    public Ping() throws Exception {
        this.linkPingTest = ("193.104.137.133");
        this.portPingTest = 80;
        this.intervalPingTest = 10;
        this.resultsPing = new int[intervalPingTest];
        this.operations = new Operations();
    }

    public void RunPingTest() {

        Socket socket;
        for (int i = 0; i < intervalPingTest; i++) {
            try {
                Stopwatch timer = new Stopwatch();
                socket = new Socket(linkPingTest, portPingTest);
                socket.close();
                resultsPing[i] = timer.elapsedTime();
                Log.d("Ping", "Valore ping: "+i+" " +resultsPing[i]+" ms");

            } catch (Exception ex) {
                resultsPing[i] = -1;
                ex.printStackTrace();
                Log.d("Ping", "test fallito");
            }
        }

        Log.d("Ping", "Valore massimo ping " + operations.max(resultsPing) + " ms");
        Log.d("Ping", "Valore minimo ping " + operations.min(resultsPing) + " ms");
        Log.d("Ping", "Media valori ping " + operations.media(resultsPing) + " ms");


    }

    public int[] getResultsPing(){
        return resultsPing;
    }

    public double getMedia(){
        return operations.media(resultsPing);
    }

}