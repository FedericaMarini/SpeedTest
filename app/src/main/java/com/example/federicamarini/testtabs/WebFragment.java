package com.example.federicamarini.testtabs;

import android.content.Context;
import android.net.Uri;
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
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        WebView myWebView = (WebView) getView().findViewById(R.id.webview);
        Stopwatch timer = new Stopwatch();
        myWebView.loadUrl(linkWebTest);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        timeElapsed = timer.elapsedTime();
        this.messageText = (TextView) getView().findViewById(R.id.messageText);

        if(timeElapsed <timeOutWebTest){
            Log.d("WEB ACTIVITY", "tempo trascorso apertura pagina web: " +timeElapsed+ " ms");
            this.messageText.setText(timeElapsed+" ms");

        }
        else {
            Log.d("WEB ACTIVITY", "fallimento apertura pagina web");
            this.messageText.setText("Timeout");

        }
    }
}
