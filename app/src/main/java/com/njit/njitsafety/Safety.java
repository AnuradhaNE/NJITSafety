package com.njit.njitsafety;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;
import android.widget.Toast;

public class Safety extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety);
        Notification notification;
        NotificationManager mNotificationManager;
        long when = System.currentTimeMillis();
        RemoteViews contentView;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        int n=getResources().getInteger(R.integer.safety_notification);
        contentView = new RemoteViews(getPackageName(), R.layout.safetynotification);
        Intent reboot=new Intent("com.example.app.ACTION_PLAY");
        reboot.putExtra("DO", "reboot");
        PendingIntent pReboot = PendingIntent.getBroadcast(this, 100, reboot, 0);
        contentView.setImageViewResource(R.id.callE,R.drawable.notifications_panic);
        contentView.setOnClickPendingIntent(R.id.callE,pReboot);

        PendingIntent P = PendingIntent.getActivity(this, 10,new Intent(this,Safety.class), 0);



        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


  // contentView.setInt(R.id.callE, "change", R.drawable.i);

        notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContent(contentView)
                 .setContentIntent(P)
                .build();;



        notification.flags |= Notification.FLAG_NO_CLEAR;
        mNotificationManager.notify(n, notification);

    }

    public static class SBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equalsIgnoreCase("com.example.app.ACTION_")){
                Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show();
                Log.d("Action","done");


            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_safety, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
//        // set the custom action
//        intentFilter.addAction("com.example.app.ACTION_PLAY");
//        registerReceiver(broadcastReceiver, intentFilter);
// contentView.setInt();
//Do not clear the notification