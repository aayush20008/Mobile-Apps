package com.example.mysensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView value;
    ToggleButton  button1, button2,button3;
    SensorManager sensorManager ;
    HelperClass dbhelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = (TextView) findViewById(R.id.textview);
        button1 = (ToggleButton) findViewById(R.id.toggle1);
        button2 = (ToggleButton) findViewById(R.id.toggle2);
        button3 = (ToggleButton) findViewById(R.id.toggle3);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        dbhelper = HelperClass.getDB(this);


        if(sensorManager != null){
            Sensor proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            Sensor light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            Sensor geo = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);

            if(proximity != null){
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(button1.isChecked()){
                            sensorManager.registerListener(MainActivity.this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
                        }
                        else{
                            sensorManager.unregisterListener(MainActivity.this,proximity);
                        }
                    }
                });
            }
            if(light != null){
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (button2.isChecked()){
                            sensorManager.registerListener(MainActivity.this,light,SensorManager.SENSOR_DELAY_NORMAL);
                        }
                        else{
                            sensorManager.unregisterListener(MainActivity.this,light);
                        }
                    }
                });

            }
            if(geo != null){
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(button3.isChecked()){
                            sensorManager.registerListener(MainActivity.this,geo,SensorManager.SENSOR_DELAY_NORMAL);
                        }
                        else {
                            sensorManager.unregisterListener(MainActivity.this,geo);
                        }
                    }
                });

            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            if(event.values[0] == 0.0){
                dbhelper.dataDAO().addto(new Data("" + event.values[0], "Object is near"));
                Log.e("Data","Object is near and value of proximity sensor is " + event.values[0]);
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            if(event.values[0] < 6){
                dbhelper.lightDAO().addto(new Light(event.values[0]));
                Log.e("Light","value is " + event.values[0]);
            }

            ArrayList<Light> arr = (ArrayList<Light>) dbhelper.lightDAO().getAllLight();
        }
        if(event.sensor.getType() == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR){
            dbhelper.geoDAO().addto(new GeoMag(event.values[2]));
            float vec = event.values[2] * 180;
            if(vec < 6 && vec > -6  ){
                value.setText("Phone is aligned to magnetic north");
                Log.e("Geo","Data stored value is " + vec);
            }
            else if (vec > 6){
                value.setText("Rotate your phone by " + vec + " towards Right");
                Log.e("Geo1","Data value is " + vec);
            }
            else if(vec < -6){
                value.setText("Rotate your phone by " + vec + " towards Left");
                Log.e("Geo2","Data value is " + vec);
            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}