package com.healthslife.pedometer.util;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.TimeZone;

import com.healthslife.BuildConfig;
import com.healthslife.pedometer.main.Database;


/**
 * Class to look for changes in the timezone setting and to adjust the database on a change
 */
public class TimeZoneListener extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        SharedPreferences prefs =
                context.getSharedPreferences("pedometer", Context.MODE_MULTI_PROCESS);
        TimeZone oldTimeZone =
                TimeZone.getTimeZone(prefs.getString("timezone", TimeZone.getDefault().getID()));
        TimeZone newTimeZone = TimeZone.getTimeZone(intent.getStringExtra("time-zone"));
        if (BuildConfig.DEBUG) {
            Logger.log("timezone changed: new: " + newTimeZone.getRawOffset() + " old: " +
                    oldTimeZone.getRawOffset());
        }
        Database db = Database.getInstance(context);
        db.timeZoneChanged(newTimeZone.getRawOffset() - oldTimeZone.getRawOffset());
        db.close();
        prefs.edit().putString("timezone", intent.getStringExtra("time-zone")).commit();
    }

}
