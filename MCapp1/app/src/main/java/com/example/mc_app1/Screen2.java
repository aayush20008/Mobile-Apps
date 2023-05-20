package com.example.mc_app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Screen2 extends AppCompatActivity {
    public TextView t1,t2;
    public CheckBox cb1;
    public CheckBox cb2;
    public CheckBox cb3,cb4,cb5;

    public RatingBar rb1,rb2,rb3,rb4,rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        AppCompatButton sub_btn, clr_btn;

        cb1 = (CheckBox) findViewById(R.id.cb_1);

            rb1 = (RatingBar) findViewById(R.id.rb_1);

        cb2 = (CheckBox) findViewById(R.id.cb_2);

            rb2 = (RatingBar) findViewById(R.id.rb_2);

        cb3 = (CheckBox) findViewById(R.id.cb_3);

            rb3 = (RatingBar) findViewById(R.id.rb_3);

        cb4 = (CheckBox) findViewById(R.id.cb_4);

            rb4 = (RatingBar) findViewById(R.id.rb_4);

        cb5 = (CheckBox) findViewById(R.id.cb_5);

            rb5 = (RatingBar) findViewById(R.id.rb_5);


        String name = getIntent().getStringExtra("keyname");
        String pora = getIntent().getStringExtra("keysurname");

        sub_btn = (AppCompatButton) findViewById(R.id.submitid);
        clr_btn = (AppCompatButton) findViewById(R.id.clearall);

        clr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb1.setRating(0f);
                rb2.setRating(0f);
                rb3.setRating(0f);
                rb4.setRating(0f);
                rb5.setRating(0f);

                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);

            }
        });

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Screen2.this, "Your entry has been recorded & state has been changed ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Screen2.this,Screen3.class);
                Intent data = getIntent();
                String username = data.getStringExtra("keyname");
                String poraudi = data.getStringExtra("keysurname");
                intent.putExtra("keyname",username);
                intent.putExtra("keysurname",poraudi);

                if(cb1.isChecked()){
                    float dancestars = rb1.getRating();
                    intent.putExtra("dancerating1",dancestars);
                }
                if(cb2.isChecked()){
                    float musicstars = rb2.getRating();
                    intent.putExtra("musicrating1",musicstars);
                }
                if(cb3.isChecked()){
                    float playstars = rb3.getRating();
                    intent.putExtra("playrating1",playstars);
                }
                if(cb4.isChecked()){
                    float fashionstars = rb4.getRating();
                    intent.putExtra("fashionrating1",fashionstars);
                }
                if(cb5.isChecked()){
                    float foodstars = rb5.getRating();
                    intent.putExtra("foodrating1",foodstars);
                }


                startActivity(intent);
            }
        });
    }
}