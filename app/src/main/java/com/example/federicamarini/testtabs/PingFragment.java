package com.example.federicamarini.testtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PingFragment extends Fragment implements Ping.Callback {
    private Ping ping;
    private TextView messageText;


    public PingFragment() throws Exception {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Ping Fragment", "onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        Log.d("Ping Fragment", "onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Ping Fragment", "onActivityCreated");
        messageText = (TextView) getView().findViewById(R.id.messageText);
        try {
            ping = new Ping(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ping.RunPingTest();
        messageText.setText(ping.getMedia()+" ms");

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("Ping Fragment", "setUserVisibleHint");
        }
    }

    @Override
    public void onSuccess(String data) {
        Log.d("Ping Fragment", "On Success");
    }

    @Override
    public void onError() {
        Log.d("Ping Fragment", "On Error");


    }


}
