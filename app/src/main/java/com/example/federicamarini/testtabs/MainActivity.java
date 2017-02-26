package com.example.federicamarini.testtabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private ProgressDialog progressDialog;
    private boolean finito = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Cliccato");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //finito = true;
                startActivity(intent);
            }
        });
    }

    private void setProgressDialog(View view){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Tests in progress...");
        progressDialog.setMessage("Test x di 5...");
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        if(!finito){
            progressDialog.dismiss();
        }

    }
}
