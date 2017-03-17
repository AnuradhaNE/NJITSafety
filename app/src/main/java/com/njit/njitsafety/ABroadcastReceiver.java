package com.njit.njitsafety;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Anuradha on 16-03-2017.
 */
public class ABroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equalsIgnoreCase("com.example.app.ACTION_PLAY")){
            Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show();
            Log.d("Action","done");

        }
    }
}
