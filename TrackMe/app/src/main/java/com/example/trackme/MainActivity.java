package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView,x,y,z,stide,dir,stairs;
    private long magPrevious = 0;
//    private float X,Y,Z;
    private Integer stepCount = 0;
    private final int STEP_DUR = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        stairs = findViewById(R.id.stairs);
        z = findViewById(R.id.z);
        stide = findViewById(R.id.stride);
        dir = findViewById(R.id.dir);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magneto = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        SensorEventListener stepDetector = new SensorEventListener() {
            float[] gravity;
            float[] geomagnetic;

            @SuppressLint("SetTextI18n")
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent != null && sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                    gravity = sensorEvent.values;
                    float x_acc =  sensorEvent.values[0];
                    float y_acc =  sensorEvent.values[1];
                    float z_acc = sensorEvent.values[2];
                    long curr = System.currentTimeMillis();
                    long diff = curr - magPrevious;
                    if(diff > STEP_DUR) {


                        x.setText(Float.toString(x_acc));
                        y.setText(Float.toString(y_acc));
                        z.setText(Float.toString(z_acc));

                        double magnitude = Math.sqrt(x_acc * x_acc + y_acc * y_acc + z_acc * z_acc);
//                        double MagDelta = magnitude - magPrevious;
//                        magPrevious = (long) magnitude;
                        if(z_acc > 11.2){
                            stairs.setText("You are on the stairs.");
                        }
                        if(y_acc > 0.9 && z_acc < 11.2){
                            stepCount++;
                            stairs.setText("You are on foot.");
                            textView.setText("Steps:- " + stepCount);
                        }
                        if(z_acc > 13.6){
                            stairs.setText("You are on a lift.");
                        }
                        if(z_acc < 4){
                            stairs.setText("You are on a lift.");
                        }

                        if (magnitude > 9.8) {
                            magPrevious = curr;
//                            stepCount++;

                            float height = 1.72f;
                            Stride stride = new Stride();
                            stride.StrideLength(height);

                            stide.setText(stride.StrideLength(height) + " meters");
                        }
//
                    }
                }
                if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                    geomagnetic = sensorEvent.values;
                }
                if(gravity != null && geomagnetic != null){
                    float Rotation[] = new float[9];
                    float Inclination[] = new float[9];
                    boolean ok = SensorManager.getRotationMatrix(Rotation,Inclination,gravity,geomagnetic);
                    if(ok){
                        float direction[] = new float[9];
                        SensorManager.getOrientation(Rotation,direction);
                        float azimuth = direction[0];
                        if(azimuth < 0){
                            azimuth += 2 * Math.PI;
                        }
                        float degree = (float) Math.toDegrees(azimuth);
                        if((degree >= 45 && degree < 135)){
                            dir.setText("You are moving towards East.");
                        }
                        if(degree >= 135 && degree < 225){
                            dir.setText("You are moving towards South.");
                        }
                        if((degree >= 0 && degree < 45) || (degree >= 315 && degree < 360)  ){
                            dir.setText("You are moving towards North.");
                        }
                        if(degree >= 225 && degree < 315){
                            dir.setText("You are moving towards West.");
                        }
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(stepDetector,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(stepDetector,magneto,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("StepCount", stepCount);
        editor.apply();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("StepCount", stepCount);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("StepCount",0);
    }
}