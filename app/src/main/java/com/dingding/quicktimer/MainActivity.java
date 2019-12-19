package com.dingding.quicktimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tv_time;
    TimePicker timePicker;
    final static private int SET_TIME = 0;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
    public static Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_time = findViewById(R.id.tv_time);
        timePicker = findViewById(R.id.timePicker);

    }

    public void setTime(View view) {
        Intent goTimePicker = new Intent(this, com.dingding.quicktimer.TimePicker.class);
        startActivityForResult(goTimePicker, SET_TIME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SET_TIME && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            int hr  = bundle.getInt("hr");
            int min = bundle.getInt("min");

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar)calNow.clone();
            calSet.set(Calendar.HOUR_OF_DAY, hr);
            calSet.set(Calendar.MINUTE, min);
//             calSet.set(Calendar.SECOND, 0);

            // 以下2行測試用
            calSet.set(Calendar.MINUTE, min -1 );
             calSet.set(Calendar.SECOND, calNow.get(Calendar.SECOND) + 7);


            if(calSet.compareTo(calNow) <=0){
                Toast.makeText(this, "跨天", Toast.LENGTH_SHORT).show();
                Log.d("xd123", "onActivityResult: 跨天");
                calSet.add(Calendar.DATE, 1);
            }

            tv_time.setText(String.format("鬧鐘%s", dateFormat.format(calSet.getTime())));
            setAlarm(calSet);
        }else if (requestCode == 10 && resultCode == RESULT_OK) {
            uri = data.getData();
            Log.d("xd123", "onActivityResult: uri:" + uri);

        }
    }
    private void setAlarm(Calendar targetCal) {
        Log.d("xd123", "setAlarm: "+ targetCal.getTime().toString());
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast( this.getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "設好鬧鐘:" + dateFormat.format(targetCal.getTime()), Toast.LENGTH_LONG).show();

    }

    public void add5(View view) {
        int nowMin = timePicker.getMinute();

        timePicker.setMinute(nowMin + 5);
    }

    public void openFile(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 10);
    }

}
