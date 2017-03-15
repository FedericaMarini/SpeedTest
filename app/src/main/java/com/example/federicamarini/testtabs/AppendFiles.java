package com.example.federicamarini.testtabs;

import android.util.Log;

public class AppendFiles {
    private int[] arrayFinale;
    private Ping ping;
    private Download download;
    private Upload upload;
    private int[] webResult;


    public int[] AppendResults() {
        try {
            //ping.getResultsPing();
            //download.getResultsDownload();
            //upload.getResultsUpload();

            arrayFinale = concatenaArray(ping.getResultsPing(), download.getResultsDownload(),
                    upload.getResultsUpload(), webResult);

            for(int i = 0; i<arrayFinale.length; i++){
                Log.d("Append Files ", "array finale: " +arrayFinale[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayFinale;
    }

    public int[] concatenaArray(int[] a, int[] b, int[] c, int[] d) {
        int aLen = a.length;
        int bLen = b.length;
        int cLen = c.length;
        int dLen = d.length;
        int[] arrayFin = new int[aLen+bLen+cLen+dLen];
        System.arraycopy(a, 0, arrayFin, 0, aLen);
        System.arraycopy(b, 0, arrayFin, aLen, bLen);
        System.arraycopy(c, 0, arrayFin, aLen+bLen, cLen);
        System.arraycopy(d, 0, arrayFin, aLen+bLen+cLen, dLen);
        return arrayFin;
    }

    public int[] getArrayFinale(){
        return arrayFinale;
    }
}
