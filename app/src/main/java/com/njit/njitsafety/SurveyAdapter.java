package com.njit.njitsafety;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anuradha on 27-03-2017.
 */

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ListItem> {


    String mode="null";
    ArrayList<SurveyItem> ls;
    Context c;
//    public ListAdapter(String mode) {
//        this.mode = mode;
//        ls=new ArrayList<>();
//    }

    public SurveyAdapter(String mode,  ArrayList<SurveyItem> ls, Context c) {
        this.mode = mode;
        this.ls = ls;
        this.c=c;
    }


    @Override
    public SurveyAdapter.ListItem onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.surveyitem, parent, false);

        return new ListItem(itemView);
    }

    @Override
    public void onBindViewHolder(SurveyAdapter.ListItem holder, int position) {
      final   SurveyItem
            i =  ls.get(position);


        holder.qt.setText(i.getQ());
        holder.o1.setText(i.getOp1());
        holder.o2.setText(i.getOp2());
        holder.o3.setText(i.getOp3());
        holder.o4.setText(i.getOp4());

        holder.o1.setChecked(i.isChecked(1));
        holder.o2.setChecked(i.isChecked(2));
        holder.o3.setChecked(i.isChecked(3));
        holder.o4.setChecked(i.isChecked(4));

        holder.o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.setSelected(1);
            }
        });
        holder.o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.setSelected(2);
            }
        });
        holder.o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.setSelected(3);
            }
        });
        holder.o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.setSelected(4);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ListItem extends RecyclerView.ViewHolder {

        LinearLayout body;
        TextView qt;
                RadioButton o1,o2,o3,o4;

        public ListItem(View itemView) {
            super(itemView);
             qt=(TextView) itemView.findViewById(R.id.qtext);
            o1=(RadioButton) itemView.findViewById(R.id.opt1);
            o2=(RadioButton) itemView.findViewById(R.id.opt2);
            o3=(RadioButton) itemView.findViewById(R.id.opt3);
            o4=(RadioButton) itemView.findViewById(R.id.opt4);
            body=(LinearLayout) itemView.findViewById(R.id.survey_ques);

        }
    }
}
