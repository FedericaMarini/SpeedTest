package com.example.federicamarini.testtabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PingFragment extends Fragment implements AsyncResponse{
    private Ping ping;
    private TextView messageText;
    testsCallback callback;


    public PingFragment() throws Exception {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ping = new Ping(getContext());
        ping.asyncResponse = this;
        ping.execute();
        return view;
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        callback = (testsCallback) activity;
    }

    @Override
    public void processFinish(Boolean output) {
        if(output){
            int[] risultatiPing = ping.getResultsPing();
            callback.getRisultatiPing(risultatiPing);
            messageText = (TextView) getView().findViewById(R.id.messageText);
            messageText.setText(ping.getMedia()+" ms");
        }
    }
}
