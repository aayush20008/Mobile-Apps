package com.example.mydictionary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Activity2 extends AppCompatActivity {
    TextView wordhere;
    Button audiobtn;
    MediaPlayer media;
    boolean isPlaying = false;

//    @SuppressLint("MissingInflatedId")
//    @SuppressLint("MissingInflatedId")

    class Downloader extends AsyncTask<Void, Void, Void>{

        private String audio;

        public Downloader(String audio) {
            this.audio = audio;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                RequestQueue rqstq = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringrqst = new StringRequest(Request.Method.GET, audio, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream("my_file.mp3");
                            fileOutputStream.write(Byte.parseByte(response));
                            fileOutputStream.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                rqstq.add(stringrqst);
            } finally {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

        }
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);


        JSONArray meaning = null;
        HashMap<String, ArrayList<ArrayList<String>>> hm = new HashMap<>();


        Intent intent = getIntent();
        String Word = intent.getStringExtra("word");
        Word = Word.toUpperCase();
        wordhere = findViewById(R.id.wordhere);
        wordhere.setText(Word);
        String audio = intent.getStringExtra("audio");
        String meaningArray = intent.getStringExtra("meanings");

        audiobtn = findViewById(R.id.audiobtn);
        audiobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying == true){
                    Stop();
                    isPlaying = false;
                }
                else{
                    try {
                        if(audio == ""){
                            Toast.makeText(Activity2.this, "Audio not available", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Play(audio);
                            isPlaying = true;
                            Toast.makeText(Activity2.this, "Audio downloaded successfully", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Downloader dwn = new Downloader(audio);
                dwn.execute();
            }
        });

        try {
            meaning = new JSONArray(meaningArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < meaning.length(); i++){

            ArrayList<ArrayList<String>> arr = new ArrayList<>();

            try {
                JSONObject jso2 = meaning.getJSONObject(i);
                JSONArray defs = jso2.getJSONArray("definitions");
                String POS = jso2.getString("partOfSpeech");

                for(int j = 0; j < defs.length(); j++){
                    JSONObject forDef = defs.getJSONObject(j);

                    ArrayList<String> arr2 = new ArrayList<>();
                    arr2.add(forDef.getString("definition"));
                    arr2.add(forDef.getString("synonyms"));
                    arr2.add(forDef.getString("antonyms"));

                    arr.add(arr2);
                }
                hm.put(POS,arr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Bundle bundle = new Bundle();
        bundle.putString("word",Word);
        bundle.putSerializable("hashmap",hm);

        datafragment df = new datafragment();
        df.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.MainContainer,df).commit();


    }
    private void Play(String url) throws IOException {
        media = new MediaPlayer();
        media.setAudioStreamType(AudioManager.STREAM_MUSIC);
        media.setDataSource(url);
        media.prepare();
        media.setVolume(1.0f,1.0f);
        media.setLooping(false);
        media.start();
    }
    private void Stop(){
        if (media != null) {
            try {
                media.reset();
                media.release();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        media = new MediaPlayer();
    }
}
