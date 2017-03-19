package com.njit.njitsafety;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Anuradha on 16-03-2017.
 */
public class SafetyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equalsIgnoreCase("com.example.app.ACTION_PLAY")){
            Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show();
            Log.d("Action","done");




            Notification notification;
            NotificationManager mNotificationManager;
            long when = System.currentTimeMillis();
            RemoteViews contentView;



            int n=context.getResources().getInteger(R.integer.safety_notification);
            contentView = new RemoteViews(context.getPackageName(), R.layout.safetynotification);
            Intent reboot=new Intent("com.example.app.ACTION_PLAY");
            reboot.putExtra("DO", "reboot");
            PendingIntent pReboot = PendingIntent.getBroadcast(context, 100, reboot, 0);
            contentView.setImageViewResource(R.id.callE,R.drawable.notifications_n);
            contentView.setOnClickPendingIntent(R.id.callE,pReboot);

            PendingIntent P = PendingIntent.getActivity(context, 10,new Intent(context,Safety.class), 0);



            mNotificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);


            // contentView.setInt(R.id.callE, "change", R.drawable.i);

            notification = new Notification.Builder(context)
                    .setSmallIcon(R.drawable.icon)
                    .setContent(contentView)
                    .setContentIntent(P)
                    .build();;



            notification.flags |= Notification.FLAG_NO_CLEAR;
            mNotificationManager.notify(n, notification);

        }
    }
}
