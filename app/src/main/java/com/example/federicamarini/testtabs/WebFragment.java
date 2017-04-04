package com.example.federicamarini.testtabs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class WebFragment extends Fragment {
    private String linkWebTest;
    private int timeOutWebTest;
    private int timeElapsed;
    private TextView messageText;
    testsCallback callback;
    private ProgressDialog progressDialog;


    public WebFragment() {
        // Required empty public constructor
        this.linkWebTest = "http://www.fub.it";
        this.timeOutWebTest = 10000;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("In progress...");
        progressDialog.setTitle("Web");
        progressDialog.setCancelable(false);
        progressDialog.show();
        WebView myWebView = (WebView) getView().findViewById(R.id.webview);
        Stopwatch timer = new Stopwatch();
        myWebView.loadUrl(linkWebTest);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        timeElapsed = timer.elapsedTime();
        progressDialog.dismiss();
        this.messageText = (TextView) getView().findViewById(R.id.messageText);

        if(timeElapsed <timeOutWebTest){
            Log.d("WEB ACTIVITY", "tempo trascorso apertura pagina web: " +timeElapsed+ " ms");
            this.messageText.setText(timeElapsed+" ms");
            callback.getRisultatoWeb(timeElapsed);

        }
        else {
            Log.d("WEB ACTIVITY", "fallimento apertura pagina web");
            this.messageText.setText("Timeout");
        }
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        callback = (testsCallback) activity;
    }

}
