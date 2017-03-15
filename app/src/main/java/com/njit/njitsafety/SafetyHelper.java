package com.njit.njitsafety;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Anuradha on 15-03-2017.
 */

public class SafetyHelper extends Activity {

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String action = (String) getIntent().getExtras().get("DO");
        if (action.equals("radio")) {
            //Your code
        } else if (action.equals("volume")) {
            //Your code
        } else if (action.equals("reboot")) {
            //Your code
            Log.d("YAY","CLICked \n Clicked");
            Toast.makeText(this, "lol", Toast.LENGTH_SHORT).show();
        }
    }
}
