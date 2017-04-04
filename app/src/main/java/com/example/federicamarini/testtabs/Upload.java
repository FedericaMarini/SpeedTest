package com.example.federicamarini.testtabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Upload extends AsyncTask<Void, Void, Boolean> {
    private String linkUploadTest;
    private int[] resultsUpload;
    private Operations operations;
    private FakeFile fakefile;
    private int numBytes;
    private ProgressDialog progressDialog;
    Context context;
    public AsyncResponse asyncResponse;

    public Upload(Context context) {
        this.linkUploadTest = "http://193.104.137.133:8080"; //server test: 193.104.137.133
        this.operations = new Operations();
        this.fakefile = new FakeFile();
        this.numBytes = 1024*1024;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Upload");
        progressDialog.setMessage("In progress...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        RunUploadTest();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean s) {
        if(progressDialog!= null) progressDialog.dismiss();
        asyncResponse.processFinish(s);
    }

    public void RunUploadTest() {

        try {
            URL url = new URL(linkUploadTest);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //parameters
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("content-type", "multipart/form-data");
            conn.connect();


            String sFile = fakefile.read(numBytes);
            Log.d("Upload", "SFile: "+sFile);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(sFile);

            int responseCode = conn.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while((inputLine=in.readLine())!=null){
                    response.append(inputLine);
                }

                in.close();
                String risposta = response.toString();
                Log.d("Upload", "Stringa: "+risposta);

                resultsUpload = separazioneStringaToInt(risposta);

                Log.d("Upload", "Massimo numero byte caricati " +operations.max(resultsUpload)+" ms");
                Log.d("Upload", "Minimo numero byte caricati " +operations.min(resultsUpload)+" ms");
                Log.d("Uploaf", "Media byte caricati " +operations.media(resultsUpload)+" ms");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] separazioneStringaToInt(String s){
        String str = modellazioneStringa(s);
        String finalString = cancellazioneSpazi(str);
        String[] arrayString = finalString.split(",");
        int[] arrayInt = new int[arrayString.length];
        for (int i = 0; i < arrayString.length; i++) {
            arrayInt[i] = Integer.parseInt(arrayString[i]);
        }
        return arrayInt;
    }

    public String modellazioneStringa(String s){
        String str = s.substring(1, s.length()-1);
        return str;
    }

    public String cancellazioneSpazi(String s){
        String str = s.replaceAll("\\s", "");
        return str;
    }

    public int[] getResultsUpload() {
        return resultsUpload;
    }

    public double getMedia(){
        return operations.media(resultsUpload);
    }

}