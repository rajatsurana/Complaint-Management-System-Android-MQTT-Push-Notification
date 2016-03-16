package com.rajat.compmsys.mqtt;

/**
 * Created by Rajat on 03-03-2016.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Calendar;

public class backgroundservice extends Service {

    Intent in;
    @Override
    public void onCreate() {

        Log.i("rajat", "backgroundservice alarm service called");
        Calendar subchecktime = Calendar.getInstance();
        subchecktime.setTimeInMillis(System.currentTimeMillis());
        //12hours
        //30 minutes
        //15 min  60000---> is min
        Intent sub = new Intent(backgroundservice.this,MQTTService.class);
        //sub.pu
        PendingIntent subscr = PendingIntent.getService(backgroundservice.this, 0, sub, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, subchecktime.getTimeInMillis(), 1000, subscr);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        in=intent;

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Intent restartServiceIntent = new Intent(getApplicationContext(),
                this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(
                getApplicationContext(), 1, restartServiceIntent,
                PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext()
                .getSystemService(Context.ALARM_SERVICE);
        alarmService.set(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);

        super.onTaskRemoved(rootIntent);
    }
}

