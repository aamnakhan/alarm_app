package com.aamnakhan.alarm_app;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class RingtoneService extends Service {

    MediaPlayer song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.e("inside the ringtone service", "yay");
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.e("in the service", "ringtone service");

        song = MediaPlayer.create(getApplicationContext(),R.raw.roundmidnight);
        song.start();

        return START_NOT_STICKY;
    }
}
