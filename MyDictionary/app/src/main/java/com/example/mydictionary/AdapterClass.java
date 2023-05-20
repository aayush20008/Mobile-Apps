package com.example.mydictionary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    String Word;
    ArrayList<String> posArray = new ArrayList<>();

    HashMap<String,ArrayList<ArrayList<String>>> hash = new HashMap<>();

    public AdapterClass(String word, HashMap<String,ArrayList<ArrayList<String>>> hash) {
        this.hash = hash;
        this.Word = word;

        for(Map.Entry<String, ArrayList<ArrayList<String>>> entry : hash.entrySet()){
            posArray.add(entry.getKey());
        }
    }

    @Override
    public AdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int position) {

        String sno = String.valueOf(position + 1);
        String item = posArray.get(position);

        holder.view1.setText(sno);
        holder.view2.setText(item);
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.click){

                    int curr = holder.getAbsoluteAdapterPosition();
                    String currpos = posArray.get(curr);

                    Bundle bundle = new Bundle();
                    bundle.putString("Word", Word);
                    bundle.putString("pos",currpos);
                    bundle.putSerializable("hashmap", hash);

                    detail det = new detail();
                    det.setArguments(bundle);

                    FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.MainContainer, det);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return hash.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView view1, view2;
        public Button click;
//        private Button click;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view1  = itemView.findViewById(R.id.itemview);
            view2 = itemView.findViewById(R.id.itemview2);
            click = itemView.findViewById(R.id.click);
        }
    }
}
