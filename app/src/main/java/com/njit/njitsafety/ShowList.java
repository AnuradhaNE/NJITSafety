package com.njit.njitsafety;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {

    TextView textView;
    RecyclerView rv;
    ArrayList<ListObj> ls;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);
        textView=(TextView)findViewById(R.id.list_title);
        rv=(RecyclerView)findViewById(R.id.list);
        textView.setText(getIntent().getStringExtra("TAG"));
        ls=new ArrayList<>();
        getList(getIntent().getStringExtra("TAG"));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listAdapter=new ListAdapter(getIntent().getStringExtra("TAG"),ls,this);




        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    void getList(String m)
    {
        switch (m)
        {
            case "Chats":
                ls.add(new ListObj("Paul","21:00",getIntent().getStringExtra("TAG")));
                ls.add(new ListObj("Rutvi","9:30",getIntent().getStringExtra("TAG")));
                break;
            case "Surveys":
                ls.add(new ListObj("Harrison:Safety Survey","",getIntent().getStringExtra("TAG")));
                ls.add(new ListObj("Orange county:Safety Survey","",getIntent().getStringExtra("TAG")));
                break;
            case "Timeline":
                ls.add(new ListObj("Survey at 9","21:00",getIntent().getStringExtra("TAG")));
                ls.add(new ListObj("App Installed","9:30",getIntent().getStringExtra("TAG")));
                break;
            case "Get Updates":
                ls.add(new ListObj("Notify Police","9:30",getIntent().getStringExtra("TAG")));
                break;
        }
    }

}
