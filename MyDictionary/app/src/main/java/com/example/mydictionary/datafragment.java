package com.example.mydictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class datafragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public datafragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_datafragment, container, false);

        RecyclerView recycler = view.findViewById(R.id.rec_view);
        Bundle bund =  getArguments();

        String word = bund.getString("word");
        HashMap<String, ArrayList<ArrayList<String>>> hm = (HashMap<String, ArrayList<ArrayList<String>>>) bund.getSerializable("hashmap");


        AdapterClass myadapter = new AdapterClass(word,hm);
        RecyclerView.LayoutManager layoutmanager =  new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutmanager);
        recycler.setAdapter(myadapter);

//        detail det = new detail();
//        det.setArguments(bund);
//        getParentFragmentManager().beginTransaction().replace(R.id.MainContainer,det).commit();

        return view;
    }
}