package com.example.mc_app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nametext;
    AppCompatButton nxtbtn;
    EditText roletext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nametext = (EditText) findViewById(R.id.nametext);
        nxtbtn = (AppCompatButton) findViewById(R.id.next_btn_text);
        roletext = (EditText)  findViewById(R.id.roletext);

        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = nametext.getText().toString();
                String poraudi = roletext.getText().toString();
                Intent intent = new Intent(MainActivity.this,Screen2.class);
                intent.putExtra("keyname",username);
                intent.putExtra("keysurname",poraudi);
                startActivity(intent);
               // String fname = nametext.getText().toString();
                Toast.makeText(MainActivity.this, "State of activity changed ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}