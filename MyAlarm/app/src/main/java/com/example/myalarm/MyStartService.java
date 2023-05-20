package com.example.myalarm;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyStartService extends Service {
    public MyReceiver myreceiver;
    static final String TAG = "STARTED SERVICE";
    public Timer timer;
    public MediaPlayer mp3;


    Boolean isplaying1,isplaying2;
    String time1,time2;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"Service is started");
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(MyStartService.this, "Service has come into action", Toast.LENGTH_SHORT);
                Log.i(TAG, "Service is started");
                toast.show();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myreceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        registerReceiver(myreceiver, filter);

        Log.i(TAG,"Service has completed the task");
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
        String inp_time = intent.getStringExtra("Key");
        String inputtime = intent.getStringExtra("Key2");

        if(inp_time == null){
            time2 = inputtime;
            isplaying2 = false;
        }
        else if (inputtime == null){
            time1 = inp_time;
            isplaying1 = false;
        }
        else{
            time2 = inputtime;
            isplaying2 = false;
            time1 = inp_time;
            isplaying1 = false;
        }

        mp3 = MediaPlayer.create(this,R.raw.cutryan);

       timer = new Timer();
       timer.scheduleAtFixedRate(new TimerTask() {
           @Override
           public void run() {
               Calendar c = Calendar.getInstance();
               SimpleDateFormat sdate = new SimpleDateFormat("HH:mm");
               String curr_time = sdate.format(c.getTime());

               if(time1 != null && curr_time.equals(time1) && isplaying1 == false){
                   StartService();
               }
               else if (time2 != null && curr_time.equals(time2) && isplaying2 == false){
                   StartServicefor2();
               }
           }
       },0,10000);


        return START_STICKY;
    }

    public void StartService() {
        //MediaPlayer mp3;
//        mp3 = MediaPlayer.create(this,R.raw.micromax);
        mp3.start();
        isplaying1 = true;
        time1 = null;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(MyStartService.this, "It's time to ring", Toast.LENGTH_SHORT);
                Log.i(TAG, "Music is playing");
                toast.show();
            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mystopService();
            }
        },10000);
    }

    public void StartServicefor2() {
        //MediaPlayer mp3;
//        mp3 = MediaPlayer.create(this,R.raw.micromax);
        mp3.start();
        isplaying2 = true;
        time2 = null;

        // Toast.makeText(this, "Music is playing", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(MyStartService.this, "It's time to ring", Toast.LENGTH_SHORT);
                Log.i(TAG, "Music is playing");
                toast.show();
            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mystopService();
            }
        },10000);
    }

    public void mystopService() {
        mp3.pause();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(MyStartService.this, "Music stopping...", Toast.LENGTH_SHORT);
                Log.i(TAG, "Music is stopped");
                toast.show();
            }
        });
//        stopSelf();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        mp3.pause();
        unregisterReceiver(myreceiver);
        Log.i(TAG,"Service has been destroyed");
        Toast.makeText(this, "Service is closed", Toast.LENGTH_SHORT).show();
    }
}