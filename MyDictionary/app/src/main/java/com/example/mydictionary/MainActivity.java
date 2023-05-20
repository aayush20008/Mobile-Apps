package com.example.mydictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;


    class HttpRequest extends AsyncTask<String, Void, Void> {
        private String word;
        public HttpRequest(String word) {
            this.word = word;
        }

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {

            String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
            Log.i("WORD",word);
            RequestQueue rqstqueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest strque = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsobj = jsonArray.getJSONObject(0);

                        String word = jsobj.getString("word");
                        String audiolink = jsobj.getJSONArray("phonetics").getJSONObject(0).getString("audio");

                        JSONArray meaning = jsobj.getJSONArray("meanings");
                        String meaningsArray = meaning.toString();

                        Intent intent = new Intent(getApplicationContext(),Activity2.class);
                        intent.putExtra("word",word);
                        intent.putExtra("audio",audiolink);
                        intent.putExtra("meanings",meaningsArray);
                        Log.i("SA","before start activity");

                        startActivity(intent);

                        Log.i("MainAct","intent is running");


//                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        Log.i("Mylog", jsonArray.getJSONObject(0).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Mylog", "Error occurred !!!");
                }
            });
            rqstqueue.add(strque);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

        }
    }

        EditText editText;
        AppCompatButton searchbtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            editText = (EditText) findViewById(R.id.editText);
            searchbtn = (AppCompatButton) findViewById(R.id.searcbtn);

            searchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String myword = editText.getText().toString().toLowerCase();
                    HttpRequest httpRequest = new HttpRequest(myword);
                    httpRequest.execute();
                }
            });

        }
    }
