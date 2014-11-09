package com.healthslife.pedometer.main;

import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import com.healthslife.R;
import com.healthslife.pedometer.background.SensorListener;




public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        SharedPreferences prefs =
                context.getSharedPreferences("pedometer", Context.MODE_MULTI_PROCESS);
        if (!prefs.getBoolean("correctShutdown", false)) {
            // DEVICE_SHUTDOWN was not sent on shutdown -> display error message
            ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1,
                    new Builder(context).setContentTitle("Incorrect shutdown").setContentText(
                            "Use the power button to shutdown the device, otherwise the app can not save your steps!")
                            .setSubText("Click for more information").setAutoCancel(true)
                            .setContentIntent(PendingIntent.getActivity(context, 0,
                                    new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://j4velin.de/faq/index.php?app=pm"))
                                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0))
                            .setShowWhen(false).setSmallIcon(R.drawable.ic_notification).build());
        }
        // last entry might still have a negative step value, so remove that
        // row if that's the case
        Database db = Database.getInstance(context);
        db.removeNegativeEntries();
        db.saveCurrentSteps(0);
        db.close();
        prefs.edit().remove("correctShutdown").apply();

        context.startService(new Intent(context, SensorListener.class));
    }
}
