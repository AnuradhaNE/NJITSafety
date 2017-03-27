package com.njit.njitsafety;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anuradha on 27-03-2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItem> {


    String mode="null";
    ArrayList<ListObj> ls;
    Context c;
//    public ListAdapter(String mode) {
//        this.mode = mode;
//        ls=new ArrayList<>();
//    }

    public ListAdapter(String mode, ArrayList<ListObj> ls, Context c) {
        this.mode = mode;
        this.ls = ls;
        this.c=c;
    }


    @Override
    public ListAdapter.ListItem onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ListItem(itemView);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListItem holder, int position) {
        ListObj i=ls.get(position);
        holder.title.setText(i.getName());
        holder.desc.setText(i.getDesc());

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ListItem extends RecyclerView.ViewHolder {

        LinearLayout body;
        TextView desc,title;

        public ListItem(View itemView) {
            super(itemView);
            desc=(TextView) itemView.findViewById(R.id.sdesc);
            title=(TextView) itemView.findViewById(R.id.stitle);
            body=(LinearLayout) itemView.findViewById(R.id.body);

        }
    }
}
