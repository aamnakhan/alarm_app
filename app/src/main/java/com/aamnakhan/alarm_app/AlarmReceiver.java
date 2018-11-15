package com.aamnakhan.alarm_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service_intent = new Intent(context, RingtoneService.class);

        context.startService(service_intent);
    }
}
