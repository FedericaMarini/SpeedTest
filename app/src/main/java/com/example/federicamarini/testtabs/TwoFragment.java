package com.example.federicamarini.testtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TwoFragment extends Fragment {
    private Download download;
    private TextView messageText;

    public TwoFragment() {
        // Required empty public constructor
        this.download = new Download();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.download.RunDownload();
        System.out.println(this.download.getMedia()/125000+" Mbps");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        this.messageText = (TextView) getView().findViewById(R.id.messageText);
        this.messageText.setText(this.download.getMedia()/125000+" Mbps");
    }
}
