package com.example.mc_app1;

import static java.lang.System.exit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Screen3 extends AppCompatActivity {
    public AppCompatButton ext_btn;
    public TextView t1,t2,rat1,rat2,rat3,rat4,rat5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        String name = getIntent().getStringExtra("keyname");
        String pora = getIntent().getStringExtra("keysurname");
        Float rt1 = getIntent().getFloatExtra("dancerating1",0);
        Float rt2 = getIntent().getFloatExtra("musicrating1",0);
        Float rt3 = getIntent().getFloatExtra("playrating1",0);
        Float rt4 = getIntent().getFloatExtra("fashionrating1",0);
        Float rt5 = getIntent().getFloatExtra("foodrating1",0);

        t1 = (TextView) findViewById(R.id.usname);
        t2 = (TextView) findViewById(R.id.surname);
        t1.setText("Name is :- " + name);
        t2.setText("Participant/Audience :- " + pora);
        rat1 = (TextView) findViewById(R.id.rat1);
        rat1.setText("Rating for Dance is:- " + rt1);

        rat2 = (TextView) findViewById(R.id.rat2);
        rat2.setText("Rating for Music is:- " + rt2);

        rat3 = (TextView) findViewById(R.id.rat3);
        rat3.setText("Rating for Play is:- " + rt3);

        rat4 = (TextView) findViewById(R.id.rat4);
        rat4.setText("Rating for Fashion is:- " + rt4);

        rat5 = (TextView) findViewById(R.id.rat5);
        rat5.setText("Rating for Food is:- " + rt5);

        ext_btn = (AppCompatButton) findViewById(R.id.exit_btn);
        ext_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit(0);
            }
        });
    }
}