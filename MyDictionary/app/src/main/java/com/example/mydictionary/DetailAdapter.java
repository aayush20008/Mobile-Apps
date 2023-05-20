package com.example.mydictionary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {
    String Word;
    String Audio;
    HashMap<String,ArrayList<ArrayList<String>>> hash ;
    ArrayList<ArrayList<String>> newarr ;

    public DetailAdapter( String word, String audio,String pos, HashMap<String, ArrayList<ArrayList<String>>> hash) {
        Word = word;
        Audio = audio;
        this.hash = hash;
        this.newarr = hash.get(pos);
    }

    @NonNull
    @Override
    public DetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_show,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.MyViewHolder holder, int position) {
        ArrayList<String> arrayList = newarr.get(position);
        String meaning = arrayList.get(0);
        String synonym = arrayList.get(1);
        String antonym = arrayList.get(2);

        holder.meanning.setText(meaning);
        holder.syno.setText(synonym.contains("[]") ? "No synonyms" : synonym);
        holder.anto.setText(antonym.contains("[]") ? "No antonyms" : antonym);
    }

    @Override
    public int getItemCount() {
        return newarr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView meanning,syno,anto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            meanning = itemView.findViewById(R.id.meaninng);
            syno = itemView.findViewById(R.id.syno);
            anto = itemView.findViewById(R.id.anto);
        }
    }
}
