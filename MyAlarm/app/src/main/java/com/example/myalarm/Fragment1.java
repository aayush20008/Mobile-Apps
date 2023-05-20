package com.example.myalarm;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Fragment1 extends Fragment {
    public TextView textview,textview2;
    public TimePicker timepick,timepick2;
    public Button button_1, button_2, timesetter,timesetter2;

    View view;
    int hour;
    int minutes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    //    View view;
        view = inflater.inflate(R.layout.fragment_1, container, false);
        // Inflate the layout for this fragment
        textview = (TextView) view.findViewById(R.id.textview);
        textview2 = (TextView) view.findViewById(R.id.textview2);
        Intent intent = new Intent(view.getContext(),MyStartService.class);
        button_1 = (Button) view.findViewById(R.id.button_1);
        button_2 = (Button) view.findViewById(R.id.button_2);
        timepick = (TimePicker) view.findViewById(R.id.timepick);
        timepick2 = (TimePicker) view.findViewById(R.id.timepick2);

        timesetter = (Button) view.findViewById(R.id.timesetter);
        timesetter2 = (Button) view.findViewById(R.id.timesetter2);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().startService(intent);
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fragment1.this.getActivity(),MyStartService.class);
                getActivity().stopService(intent);
            }
        });

        timesetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour = timepick.getCurrentHour();
                minutes = timepick.getCurrentMinute();
                String time = Integer.toString(hour)+ ":" + Integer.toString(minutes) ;

                if(hour < 10){
                    time =  "0"+ Integer.toString(hour)+ ":" + Integer.toString(minutes) ;
                }
                if(minutes<10){
                    time = Integer.toString(hour)+ ":0" + Integer.toString(minutes) ;
                }
                textview.setText("Alarm set for " + time);

                intent.putExtra("Key",time);
                Log.i("BUTTON",time);
                Toast.makeText(getActivity(), "Time selected is " + time, Toast.LENGTH_SHORT).show();
            }
        });
        timesetter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour = timepick2.getCurrentHour();
                minutes = timepick2.getCurrentMinute();
                String time = Integer.toString(hour)+ ":" + Integer.toString(minutes) ;

                if(hour < 10){
                    time =  "0"+ Integer.toString(hour)+ ":" + Integer.toString(minutes) ;
                }
                if(minutes<10){
                    time = Integer.toString(hour)+ ":0" + Integer.toString(minutes) ;
                }
                textview2.setText("Alarm set for " + time);

                intent.putExtra("Key2",time);
                Log.i("BUTT_Ton",time);
                Toast.makeText(getActivity(), "Time selected is " + time, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}