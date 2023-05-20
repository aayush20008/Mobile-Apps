package com.example.myalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("receive", "onReceive: about to enter ");
        if (action != null) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.i("receive", "onReceive: about to enter 2");
                Intent serviceIntent = new Intent(context, MyStartService.class);
                context.stopService(serviceIntent);
            }
                if (action.equals(Intent.ACTION_POWER_CONNECTED) || action.equals(Intent.ACTION_BATTERY_LOW)) {
                    Log.i("receive", "onReceive: entered ");
                    Toast.makeText(context, "Broadcast event occurred", Toast.LENGTH_SHORT).show();
                    Intent serviceIntent = new Intent(context, MyStartService.class);
                    context.stopService(serviceIntent);
                }

        }
    }
}


