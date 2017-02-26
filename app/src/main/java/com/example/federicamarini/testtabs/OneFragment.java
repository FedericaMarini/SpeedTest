package com.example.federicamarini.testtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OneFragment extends Fragment {
    private Ping ping;
    private TextView messageText;


    public OneFragment() throws Exception {
        // Required empty public constructor
        this.ping = new Ping();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ping.RunPingTest();
        System.out.println(ping.getMedia());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        messageText = (TextView) getView().findViewById(R.id.messageText);
        messageText.setText(ping.getMedia()+" ms");
    }
}
