package com.example.notifku;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotifReceiverJava extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("MESSAGE");
        if (msg != null) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }
}




