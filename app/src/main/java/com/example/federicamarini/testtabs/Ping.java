package com.example.federicamarini.testtabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.net.Socket;

public class Ping extends AsyncTask<Void, Void, Boolean>{
    private String linkPingTest;
    private int portPingTest;
    private int intervalPingTest;
    private int[] resultsPing;
    private Operations operations;
    private ProgressDialog progressDialog;
    Context context;
    public AsyncResponse asyncResponse;


    public Ping(Context context){
        this.linkPingTest = ("193.104.137.133");//193.104.137.133
        this.portPingTest = 80;
        this.intervalPingTest = 10;
        this.resultsPing = new int[intervalPingTest];
        this.operations = new Operations();
        this.context = context;
        this.asyncResponse = null;
    }

    @Override
    protected void onPreExecute() {
        Log.d("Download", "onPreExecute");
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("In progress...");
        progressDialog.setTitle("Ping");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Log.d("Download", "doInBackground");
        RunPingTest();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean s) {
        if (progressDialog != null) progressDialog.dismiss();
        asyncResponse.processFinish(s);
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