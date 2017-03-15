package com.example.federicamarini.testtabs;

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

public class Upload {
    private String linkUploadTest;
    private String uriFile;
    private int intervalUploadTest;
    private int[] resultsUpload;
    private Operations operations;
    private FakeFile fakefile;
    private int numBytes;

    public Upload() {
        this.linkUploadTest = "http://193.104.137.133:8080"; //server test: 193.104.137.133
        this.operations = new Operations();
        this.fakefile = new FakeFile();
        this.numBytes = 1024*1024;
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
            }

        } catch (IOException e) {
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