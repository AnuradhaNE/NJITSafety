package com.njit.njitsafety;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Survey_Activity extends AppCompatActivity {

    TextView textView;
    RecyclerView rv;
    ArrayList<SurveyItem> ls;
   SurveyAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);
        textView=(TextView)findViewById(R.id.list_title);
        rv=(RecyclerView)findViewById(R.id.list);
        textView.setText(getIntent().getStringExtra("SurveyT"));
        ls=new ArrayList<>();
      final Activity C=this;
        try {
          final  JSONArray array=new JSONArray(getIntent().getStringExtra("SurveyC"));

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {

                    for(int i=0;i<10;i++)
                    {

                            ls.add(new SurveyItem("Q - "+(i+1),"o1","o2","o3","o4"));

                    }
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(C);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    listAdapter=new SurveyAdapter(C.getIntent().getStringExtra("SurveyT"),ls,C);




                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setLayoutManager(layoutManager);
                    rv.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                }
            }.execute();


        } catch (JSONException e) {

        }

    }

    void getList(String m)
    {
      //new AsyncTask<Void,Void,JS>().execute();
    }

}
