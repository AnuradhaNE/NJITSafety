package com.njit.njitsafety;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class SafetyService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public  final String SAFETY_N = "com.njit.njitsafety.action.Safety_Normal";
    public  final String  SAFETY_Y= "com.njit.njitsafety.action.Safety_Unsafe";
    public  final String  SAFETY_P = "com.njit.njitsafety.action.Safety_Panic";
    public  final String  Call = "com.njit.njitsafety.action.Call";

    // TODO: Rename parameters
    public  final String EXTRA_PARAM1 = "com.njit.njitsafety.extra.PARAM1";
    public  final String EXTRA_PARAM2 = "com.njit.njitsafety.extra.PARAM2";

    public SafetyService() {
        super("SafetyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (SAFETY_Y.equals(action)) {

                Log.d("Toast","Unsafe");













                Notification notification;
                NotificationManager mNotificationManager;
                long when = System.currentTimeMillis();
                RemoteViews contentView;
//                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                setSupportActionBar(toolbar);


                int n=getResources().getInteger(R.integer.safety_notification);
                contentView = new RemoteViews(getPackageName(), R.layout.safetynotification);


                Intent panic=new Intent(this,SafetyService.class);
                panic.setAction("com.njit.njitsafety.action.Safety_Panic");
                PendingIntent Pa = PendingIntent.getService(this, 100, panic, 0);
                //com.njit.njitsafety.action.FOO
                Intent Call=new Intent(this,SafetyService.class);
                Call.setAction("com.njit.njitsafety.action.Call");
                PendingIntent pendingIntentCall = PendingIntent.getService(this, 1001, Call, 0);


                Intent Close=new Intent(this,SafetyService.class);
                Close.setAction("com.njit.njitsafety.action.Safety_Normal");
                contentView.setImageViewResource(R.id.callE,R.drawable.notifications_un);
                contentView.setTextViewText(R.id.safety_stats,"Call Emergency \n Contacts");
                contentView.setViewVisibility(R.id.close_btn, View.VISIBLE);
                PendingIntent cl = PendingIntent.getService(this, 10401, Close, 0);

                contentView.setOnClickPendingIntent(R.id.call,pendingIntentCall);
                contentView.setOnClickPendingIntent(R.id.callE,Pa);
                contentView.setOnClickPendingIntent(R.id.close_btn,cl);


                mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


                // contentView.setInt(R.id.callE, "change", R.drawable.i);
                PendingIntent CI = PendingIntent.getActivity(this, 1080, new Intent(this,Safety.class), 0);
                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContent(contentView)
                        .setContentIntent(CI)
                        .build();;



                notification.flags |= Notification.FLAG_NO_CLEAR;
                mNotificationManager.notify(n, notification);

















                // Toast.makeText(this, "AA", Toast.LENGTH_SHORT).show();
               // handleActionFoo(param1, param2);
            } else if (SAFETY_P.equals(action)) {



                Log.d("Toast","Panic");













                Notification notification;
                NotificationManager mNotificationManager;
                long when = System.currentTimeMillis();
                RemoteViews contentView;
//                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                setSupportActionBar(toolbar);


                int n=getResources().getInteger(R.integer.safety_notification);
                contentView = new RemoteViews(getPackageName(), R.layout.safetynotification);


                Intent panic=new Intent(this,SafetyService.class);
                panic.setAction("com.njit.njitsafety.action.Safety_P");
                PendingIntent Pa = PendingIntent.getService(this, 100, panic, 0);

                Intent Close=new Intent(this,SafetyService.class);
                Close.setAction("com.njit.njitsafety.action.Safety_Normal");
                PendingIntent cl = PendingIntent.getService(this, 10401, Close, 0);

                //com.njit.njitsafety.action.FOO
                Intent Call=new Intent(this,SafetyService.class);
                Call.setAction("com.njit.njitsafety.action.Call");

                PendingIntent pendingIntentCall = PendingIntent.getService(this, 1001, Call, 0);
                contentView.setImageViewResource(R.id.callE,R.drawable.notifications_panic);
                contentView.setTextViewText(R.id.safety_stats,"Call Authorities");
                contentView.setViewVisibility(R.id.close_btn, View.VISIBLE);
                contentView.setOnClickPendingIntent(R.id.call,pendingIntentCall);
                contentView.setOnClickPendingIntent(R.id.callE,Pa);
                contentView.setOnClickPendingIntent(R.id.close_btn,cl);

              //  contentView.setViewVisibility();
                mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


                // contentView.setInt(R.id.callE, "change", R.drawable.i);
                PendingIntent CI = PendingIntent.getActivity(this, 1080, new Intent(this,Safety.class), 0);
                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContent(contentView)
                        .setContentIntent(CI)
                        .build();;



                notification.flags |= Notification.FLAG_NO_CLEAR;
                mNotificationManager.notify(n, notification);



            }
            else if (SAFETY_N.equals(action)) {




                Log.d("Toast","Normalize");













                Notification notification;
                NotificationManager mNotificationManager;
                long when = System.currentTimeMillis();
                RemoteViews contentView;
//                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                setSupportActionBar(toolbar);


                int n=getResources().getInteger(R.integer.safety_notification);
                contentView = new RemoteViews(getPackageName(), R.layout.safetynotification);


                Intent panic=new Intent(this,SafetyService.class);
                panic.setAction("com.njit.njitsafety.action.Safety_Unsafe");
                PendingIntent Pa = PendingIntent.getService(this, 100, panic, 0);
                //com.njit.njitsafety.action.FOO
                Intent Call=new Intent(this,SafetyService.class);
                Call.setAction("com.njit.njitsafety.action.Call");
                PendingIntent pendingIntentCall = PendingIntent.getService(this, 1001, Call, 0);


               // Intent Close=new Intent(this,SafetyService.class);
              //  Close.setAction("com.njit.njitsafety.action.Safety_Normal");
                contentView.setImageViewResource(R.id.callE,R.drawable.notifications_n);
                contentView.setTextViewText(R.id.safety_stats,"Welcome to \n NJITShield");
                contentView.setViewVisibility(R.id.close_btn, View.GONE);
                //PendingIntent cl = PendingIntent.getService(this, 10401, Close, 0);

                contentView.setOnClickPendingIntent(R.id.call,pendingIntentCall);
                contentView.setOnClickPendingIntent(R.id.callE,Pa);
              //  contentView.setOnClickPendingIntent(R.id.close_btn,cl);


                mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


                // contentView.setInt(R.id.callE, "change", R.drawable.i);
                PendingIntent CI = PendingIntent.getActivity(this, 1080, new Intent(this,Safety.class), 0);
                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContent(contentView)
                        .setContentIntent(CI)
                        .build();;



                notification.flags |= Notification.FLAG_NO_CLEAR;
                mNotificationManager.notify(n, notification);

















                // Toast.makeText(this, "AA", Toast.LENGTH_SHORT).show();
                // handleActionFoo(param1, param2);








            }

            else if (Call.equals(action)) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                               "Call Emergency Service",
                                Toast.LENGTH_SHORT).show();
                    }
                });            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
