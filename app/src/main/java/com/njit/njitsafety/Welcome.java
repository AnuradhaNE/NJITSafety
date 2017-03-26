package com.njit.njitsafety;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


    }

    @Override
    protected void onResume() {
        super.onResume();
        final Context c=this;
        if (PrefUtils.getIntVal("AGE",this)>16) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i=new Intent(c,Safety.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                  // Toast.makeText(c, "Go Delayed", Toast.LENGTH_SHORT).show();
                }
            },900 );
        }
    }

    public void go(View v)
    {

        Intent i=new Intent(this,Signup.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        Toast.makeText(this, "Go", Toast.LENGTH_SHORT).show();
    }
}
