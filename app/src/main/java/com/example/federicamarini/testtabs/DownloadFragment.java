package com.example.federicamarini.testtabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DownloadFragment extends Fragment implements AsyncResponse{
    private Download download;
    private TextView messageText;
    testsCallback callback;

    public DownloadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        download = new Download(getContext());
        download.asyncResponse = this;
        download.execute();
        return view;
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        callback = (testsCallback) activity;
    }

    @Override
    public void processFinish(Boolean output) {
        if(output) {
            int[] risultatiDownload = download.getResultsDownload();
            callback.getRisultatiDownload(risultatiDownload);
            this.messageText = (TextView) getView().findViewById(R.id.messageText);
            this.messageText.setText(this.download.getMedia()/125000+" Mbps");
        }
    }
}
