package com.example.federicamarini.testtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ThreeFragment extends Fragment {
    private Upload upload;
    private TextView messageText;

    public ThreeFragment() {

        // Required empty public constructor
        this.upload = new Upload();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.upload.RunUploadTest();
        System.out.println(this.upload.getMedia()/125000+" Mbps");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        this.messageText = (TextView) getView().findViewById(R.id.messageText);
        this.messageText.setText(this.upload.getMedia()/125000+" Mbps");
    }
}