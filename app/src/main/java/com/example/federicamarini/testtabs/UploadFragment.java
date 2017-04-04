package com.example.federicamarini.testtabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class UploadFragment extends Fragment implements AsyncResponse{
    private Upload upload;
    private TextView messageText;
    testsCallback callback;

    public UploadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        upload = new Upload(getContext());
        upload.asyncResponse = this;
        upload.execute();
        return view;
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        callback = (testsCallback) activity;
    }

    @Override
    public void processFinish(Boolean output) {
        if(output){
            int[] risultatiUpload = upload.getResultsUpload();
            callback.getRisultatiUpload(risultatiUpload);
            this.messageText = (TextView) getView().findViewById(R.id.messageText);
            this.messageText.setText(this.upload.getMedia()/125000+" Mbps");
        }
    }
}