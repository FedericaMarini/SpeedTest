package com.example.federicamarini.testtabs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoFragment extends Fragment {
    private String linkVideoTest;
    private int timeOutVideoTest = 240000; //in ms
    private VideoView myVideoView;
    private MediaController mediaController;
    private int contentLength;
    private int cont;
    private int[] resultsVideo;
    private int[] arrayRisultatiCompleto;
    private TextView messageText;
    private int time;
    testsCallback callback;
    private int numeroValori;
    private ProgressDialog progressDialog;


    public VideoFragment() {
        // Required empty public constructor
        this.linkVideoTest = "http://ia800201.us.archive.org/22/items/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        this.timeOutVideoTest = 240000; //in ms
        this.contentLength = 5728124;
        this.cont = 0;
        this.numeroValori = 0;
        this.resultsVideo = new int[200];
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_five, container, false);
        return view;
    }

    @Override
    public void onStart(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("In progress...");
        progressDialog.setTitle("Streaming Video");
        progressDialog.setCancelable(false);
        progressDialog.show();
        super.onStart();
        myVideoView = (VideoView) getView().findViewById(R.id.videoview);
        this.messageText = (TextView) getView().findViewById(R.id.messageText);

        final Stopwatch timer = new Stopwatch();
        try {
            Uri uriVideo = Uri.parse(linkVideoTest);
            myVideoView.setVideoURI(uriVideo);

        } catch (Exception e) {
            messageText.setText("Fallimento apertura video");
            Log.d("VIDEO ACTIVITY", "Fallimento apertura video");
            e.printStackTrace();
        }

        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(final MediaPlayer mediaPlayer) {
                myVideoView.start();
                if (mediaController == null){
                    mediaController = new MediaController(getContext());
                }
                mediaController.setAnchorView(myVideoView);
                myVideoView.setMediaController(mediaController);

                time = timer.elapsedTime();

                if (time < timeOutVideoTest){
                    Log.d("VIDEO ACTIVITY", "Tempo trascorso apertura video: " +time+ "ms");
                    messageText.setText("First frame: "+time+ " ms");
                    callback.getFirstFrame(time);
                }
                else messageText.setText("Timeout");
                mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    boolean fin = false;
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        if(percent <100) {
                            Log.d("VIDEO ACTIVITY", "Buffering update: " + percent + " %");
                            int byteBuffer = (contentLength * percent) / 100;
                            Log.d("VIDEO ACTIVITY", "Buffering update: " + byteBuffer + " bytes");
                            resultsVideo[cont] = byteBuffer;
                            Log.d("VIDEO ACTIVITY", "ARRAY: " + cont + " " + resultsVideo[cont] + " Bytes");
                            cont++;
                        }
                        else
                        {
                            progressDialog.dismiss();
                            if(!fin) {
                                finito(resultsVideo);
                                fin = true;
                                for(int i = 0; i<resultsVideo.length; i++){
                                    if(resultsVideo[i]>0){
                                        numeroValori++;
                                    }
                                }
                                arrayRisultatiCompleto = new int[numeroValori+1];
                                for (int j=0; j<resultsVideo.length; j++) {
                                    if (resultsVideo[j] > 0) {
                                        arrayRisultatiCompleto[j] = resultsVideo[j];
                                    }
                                }
                                callback.getRisultatiVideo(arrayRisultatiCompleto);
                            }
                        }

                    }
                });
            }

        });
    }



    private void finito(int results[]) {
        cont ++;
        results[cont] = contentLength;
        Log.d("VIDEO ACTIVITY", "ARRAY FINITO: " + cont + " " + results[cont] + " Bytes");
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        callback = (testsCallback) activity;
    }

}
