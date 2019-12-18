package com.dingding.quicktimer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    Vibrator v;
    @Override
    public void onReceive(Context context, Intent k2) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "收到", Toast.LENGTH_LONG).show();

        v=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(3000);

    }
}
