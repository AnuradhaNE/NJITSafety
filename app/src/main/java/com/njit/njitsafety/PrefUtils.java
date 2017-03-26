package com.njit.njitsafety;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Anuradha on 26-03-2017.
 */

public class PrefUtils {
    public static boolean addToPref(String k,String v,Context c)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = PreferenceManager.getDefaultSharedPreferences(c); //1
        editor = settings.edit(); //2
        editor.putString(k,v); //3
        return editor.commit(); //4
    }

    public static boolean addToPref(String k,int v,Context c)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = PreferenceManager.getDefaultSharedPreferences(c); //1
        editor = settings.edit(); //2
        editor.putInt(k,v); //3
        return editor.commit(); //4
    }
    public static boolean addToPref(String k,boolean v,Context c)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = PreferenceManager.getDefaultSharedPreferences(c); //1
        editor = settings.edit(); //2
        editor.putBoolean(k,v); //3
        return editor.commit(); //4
    }
    public static String getStrVal(String k,Context c)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = PreferenceManager.getDefaultSharedPreferences(c); //1
        return settings.getString(k,"null");
    }

    public static int getIntVal(String k, Context c)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = PreferenceManager.getDefaultSharedPreferences(c); //1
        return settings.getInt(k,-999);
    }

    public static boolean getBooleanVal(String k, Context c)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = PreferenceManager.getDefaultSharedPreferences(c); //1
        return settings.getBoolean(k,false);
    }

}
