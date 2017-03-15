package com.example.federicamarini.testtabs;

import android.app.ProgressDialog;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.net.Socket;

public class Ping extends AsyncTask<String, Integer, String> {
    private String linkPingTest;
    private int portPingTest;
    private int intervalPingTest;
    private int[] resultsPing;
    private Operations operations;
    private Context context;
    private ProgressDialog progressDialog;
    private Callback callback;

    public Ping(Context context) throws Exception {
        this.context = context;
        this.linkPingTest = ("193.104.137.133");//193.104.137.133
        this.portPingTest = 80;
        this.intervalPingTest = 10;
        this.resultsPing = new int[intervalPingTest];
        this.operations = new Operations();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Caricamento...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (progressDialog != null) progressDialog.dismiss();
        if (callback != null) {
            if (s == null) {
                callback.onError();
            } else {
                callback.onSuccess(s);
            }
        }
    }

    public interface Callback {
        void onSuccess(String data);
        void onError();
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