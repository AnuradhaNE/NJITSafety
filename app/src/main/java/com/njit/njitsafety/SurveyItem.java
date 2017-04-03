package com.njit.njitsafety;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Anuradha on 03-04-2017.
 */

public class SurveyItem {

    String q="null",op1="null",op2="null",op3="null",op4="null";
    int selected=-9;

    public SurveyItem(String q, String op1, String op2, String op3, String op4) {
        this.q = q;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        selected=-9;
    }

    public static SurveyItem convt(JSONObject q)
    {

        try {
            return new SurveyItem(q.getString("Q"),q.getString("O1"),q.getString("O2"),q.getString("O3"),q.getString("O4"));

        } catch (JSONException e) {
            Log.e("JSONE",e.getMessage());
            return null;
        }

    }

    public boolean isChecked(int i)
    {
        return i==selected;
    }
    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getQ() {
        return q;
    }

    public static boolean set(String a)
    {
        return a.isEmpty()||a.equals("null");
    }
    public static boolean set(int a)
    {
        return a==1||a==2||a==3||a==4;
    }
    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }

    public String getOp4() {
        return op4;
    }
}
