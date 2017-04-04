package com.example.federicamarini.testtabs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Download extends AsyncTask <Void, Void, Boolean> {
    private String linkDownload;
    private int intervalDownload;
    private int[] resultsDownload;
    private Operations operations;
    private ProgressDialog progressDialog;
    Context context;
    public AsyncResponse asyncResponse;

    public Download(Context context){
        this.linkDownload = "https://ia800201.us.archive.org/22/items/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet.mp4"; //dropbox
        this.intervalDownload = 10;
        this.resultsDownload = new int[intervalDownload];
        this.operations = new Operations();
        this.context = context;
        this.asyncResponse = null;
    }


    @Override
    protected void onPreExecute() {
        Log.d("Download", "onPreExecute");
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("In progress...");
        progressDialog.setTitle("Download");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Log.d("Download", "doInBackground");
        RunDownload();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean s) {
        Log.d("Download", "onPostExecute");
        if (progressDialog != null) progressDialog.dismiss();
        asyncResponse.processFinish(s);
    }

    public void RunDownload(){
        //http connection
        URL url = null;
        try {
            url = new URL(linkDownload);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection httpConnection = null;
        InputStream input = null;
        int read = 0;
        try {
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.connect();
            if(httpConnection.getResponseCode() == httpConnection.HTTP_OK){

                //download file
                for(int i = 0; i<intervalDownload; i++){
                    try {
                        input = httpConnection.getInputStream();
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
                        byte[] buffer = new byte[512];
                        int bytesRead = 0;
                        Stopwatch timer = new Stopwatch();
                        while ((read = bufferedInputStream.read(buffer, 0, 512)) != -1 && timer.elapsedTime()<1000){
                            bytesRead +=read;
                        }
                        Log.d("Download", "Byte letti: " +bytesRead);
                        resultsDownload[i] = bytesRead;

                    } catch (IOException e) {
                        e.printStackTrace();
                        resultsDownload[i] = -1;
                        Log.d("Download", "Download fallito");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Download", "Connessione http fallita");
        }
        Log.d("Download", "Massimo numero byte scaricati " +operations.max(resultsDownload)+" ms");
        Log.d("Download", "Minimo numero byte scaricati " +operations.min(resultsDownload)+" ms");
        Log.d("Download", "Media byte scaricati " +operations.media(resultsDownload)+" ms");
    }

    public double getMedia(){
        return operations.media(resultsDownload);
    }


    public int[] getResultsDownload() {
        return resultsDownload;
    }
}