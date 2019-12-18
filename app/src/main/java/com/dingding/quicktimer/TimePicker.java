package com.dingding.quicktimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class TimePicker extends AppCompatActivity {

    android.widget.TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
    }

    public void set30(View view) {
        if(timePicker.getMinute() < 30){
            timePicker.setMinute(30);
        } else {
            timePicker.setHour(timePicker.getHour()+1);
            timePicker.setMinute(30);
        }
    }

    public void set00(View view) {
        timePicker.setHour(timePicker.getHour()+1);
        timePicker.setMinute(0);
    }
    public void finishSetting(View view){
        Intent it = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("hr", timePicker.getHour());
        bundle.putInt("min", timePicker.getMinute());
        it.putExtras(bundle);
        setResult(RESULT_OK, it);
        finish();
    }
    public void add1(View view) {
        // 取得指計上的時間
        int hr = timePicker.getHour();
        int min = timePicker.getMinute();
        // 設入日曆
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, hr);
        now.set(Calendar.MINUTE, min);
        // 日曆+5
        now.add(Calendar.MINUTE, 1);
        // 設回指計
        timePicker.setHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(now.get(Calendar.MINUTE));
    }
    public void add5(View view) {
        // 取得指計上的時間
        int hr = timePicker.getHour();
        int min = timePicker.getMinute();
        // 設入日曆
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, hr);
        now.set(Calendar.MINUTE, min);
        // 日曆+5
        now.add(Calendar.MINUTE, 5);
        // 設回指計
        timePicker.setHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(now.get(Calendar.MINUTE));
    }
    public void add10(View view) {
        // 取得指計上的時間
        int hr = timePicker.getHour();
        int min = timePicker.getMinute();
        // 設入日曆
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, hr);
        now.set(Calendar.MINUTE, min);
        // 日曆+5
        now.add(Calendar.MINUTE, 10);
        // 設回指計
        timePicker.setHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(now.get(Calendar.MINUTE));
    }
    public void add15(View view) {
        // 取得指計上的時間
        int hr = timePicker.getHour();
        int min = timePicker.getMinute();
        // 設入日曆
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, hr);
        now.set(Calendar.MINUTE, min);
        // 日曆+5
        now.add(Calendar.MINUTE, 15);
        // 設回指計
        timePicker.setHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(now.get(Calendar.MINUTE));
    }
    public void setNow(View view) {
        Calendar now = Calendar.getInstance();
        // 設回指計
        timePicker.setHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(now.get(Calendar.MINUTE));
    }
}
