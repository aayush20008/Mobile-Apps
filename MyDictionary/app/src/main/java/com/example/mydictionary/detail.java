package com.example.mydictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;


public class detail extends Fragment {

    public detail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        RecyclerView recycler = view.findViewById(R.id.rec_view_2);
        Bundle bund = getArguments();
        String word = bund.getString("Word");
        String audio = bund.getString("Audio");
        String pos = bund.getString("pos");
        HashMap<String, ArrayList<ArrayList<String>>> hm = (HashMap<String, ArrayList<ArrayList<String>>>) bund.getSerializable("hashmap");

        DetailAdapter myadapter = new DetailAdapter(word,audio,pos,hm);
        RecyclerView.LayoutManager layoutmanager =  new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutmanager);
        recycler.setAdapter(myadapter);

        return view;
    }
}