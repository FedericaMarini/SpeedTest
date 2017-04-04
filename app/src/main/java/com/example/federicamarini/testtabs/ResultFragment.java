package com.example.federicamarini.testtabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;


public class ResultFragment extends Fragment {
    private Button resultButton;
    private Button rerunButton;
    private AppendFiles appendFiles = new AppendFiles();

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();
        resultButton = (Button) getView().findViewById(R.id.ResultButton);

        resultButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*fileAbsolutePath = SaveCSV.getPath("RisultatiTest.csv");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.fromFile(fileAbsolutePath));
                intent.setType("application/vnd.ms-excel");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);*/
            }
        });

        rerunButton = (Button) getView().findViewById(R.id.RerunButton);
        rerunButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
}
