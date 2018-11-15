package com.aamnakhan.alarm_app;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker time_picker;
    Context context;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        time_picker = (TimePicker) findViewById(R.id.time_picker);

        final Calendar calendar = Calendar.getInstance();

        Button set_alarm = (Button) findViewById(R.id.set_alarm);
        Button stop_alarm = (Button) findViewById(R.id.stop_alarm);

        final Intent alarm_intent = new Intent(this.context, AlarmReceiver.class);

        set_alarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                calendar.set(Calendar.HOUR_OF_DAY,time_picker.getHour());
                calendar.set(Calendar.HOUR_OF_DAY,time_picker.getMinute());

                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, alarm_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);

            }
        });

        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                sendBroadcast(alarm_intent);
                alarm_manager.cancel(pending_intent);

            }
        });

    }

}
