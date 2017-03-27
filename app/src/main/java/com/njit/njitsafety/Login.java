package com.njit.njitsafety;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public String acc_ids[]={
      "psl9",
       "avd37",
            "sr856",
            "bd248",
            "mh449"
    };
EditText ucid,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      //  Toast.makeText(this, " "+acc_ids.length+"\n items", Toast.LENGTH_SHORT).show();
        ucid=(EditText)findViewById(R.id.ucid_);
        pwd=(EditText)findViewById(R.id.pwd);

    }

    public void login(View v){
        final Context c=this;
//        Log.e("LOLL","  "+PrefUtils.getStrVal("UCID",this)+pwd.equals("admin")+pwd);
        if(ucid.getText().toString().equals(PrefUtils.getStrVal("UCID",this))&&pwd.getText().toString().equals("admin")){
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
        else{
            Toast.makeText(this, "Not a match", Toast.LENGTH_SHORT).show();
        }
    }
}

