package com.dingding.quicktimer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.IOException;

public class AlarmReceiver extends BroadcastReceiver {
    Vibrator v;
    public int notificationId = 0;
    String CHANNEL_ID = "default_notification_channel_id";
    MediaPlayer mp = null;
    @Override
    public void onReceive(Context context, Intent k2) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "鬧鐘到！", Toast.LENGTH_LONG).show();

        v=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

        Log.d("xd123", "onReceive: 播放uri:" + MainActivity.uri);
            mp = MediaPlayer.create(context, MainActivity.uri);
//            mp.setDataSource(context, MainActivity.uri);
            Log.d("xd123", "onReceive: 播放uri:" + MainActivity.uri);
            mp.start();
            Log.d("xd123", "onReceive: 執行stare");


        v.vibrate(3000);


//        sendNotification("時間到", "給我停", context);

    }

    private void sendNotification(String messageTitle, String messageBody, Context context){


//        Intent stopInt = new Intent(this, StopMusic.class);
//        stopInt.setAction("stopMusic");
//        PendingIntent directStop = PendingIntent.getBroadcast(context, 0, stopInt, 0);

//        原本用這個單純的 Broadcast 方式
//        Intent stopInt = new Intent(this, StopMusic.class);
//        stopInt.setAction("stopMusic");
//        PendingIntent stopPendingInt = PendingIntent.getBroadcast(this, 0, stopInt, 0);

        // 顯示 ToStopMusic後，按Back鍵，要回到MainActivity的方式
        // https://www.jianshu.com/p/678e2322fd41
        // 第三種方式 addNextIntentWithParentStack 為自己看官網文件查到的
        Intent toStopIntent = new Intent(context, MainActivity.class);  // ToStopMusic.class
        toStopIntent.putExtra("showStopButton", true);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context).addNextIntentWithParentStack(toStopIntent);
        PendingIntent goToMainWithStopButton = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


//        NotificationCompat.Action stopAction = new NotificationCompat.Action.Builder(android.R.drawable.ic_media_pause, "關閉警報", directStop).build();

        NotificationCompat.Builder notificationCompatBuilder = new NotificationCompat.Builder(context
                , CHANNEL_ID)
                .setSmallIcon(android.R.drawable.alert_dark_frame) //.mipmap.ic_launcher_foreground
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .addAction(stopAction)
                .setAutoCancel(true)                        // 按下通知後，移除此通知
                .setContentIntent(goToMainWithStopButton)            // 按下通知後，打開intent
                .setFullScreenIntent(goToMainWithStopButton, true); // 把它變成這個(426抄的)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "地震警報通知", NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription("地震快來時提醒");
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(notificationId, notificationCompatBuilder.build());




    }
}
