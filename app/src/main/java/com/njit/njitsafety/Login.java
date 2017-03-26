package com.njit.njitsafety;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public String acc_ids[]={
      "psl9",
       "avd37",
            "sr856",
            "bd248",
            "mh449"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(this, " "+acc_ids.length+"\n items", Toast.LENGTH_SHORT).show();
    }
}
