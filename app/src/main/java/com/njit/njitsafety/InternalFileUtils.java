package com.njit.njitsafety;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anuradha on 02-04-2017.
 */

public class InternalFileUtils {


    public static boolean writetoFile(Context c, String append,String fname)
    {
        File file = new File(c.getFilesDir(),fname);
        FileOutputStream outputStream;

        try {
            String s=readFromFile(c,fname);
            s=s+append;
            outputStream = c.openFileOutput(fname, Context.MODE_PRIVATE);
            outputStream.write(s.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readFromFile(Context context,String fname)
    {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fname);

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                Log.d("Line",line);
            }
            return sb.toString();
        }
        catch (FileNotFoundException e) {
            Log.e("FNF",e.getMessage());
            return " ";
        }
        catch (IOException e) {
            Log.e("IO",e.getMessage());

            return " ";
        }
    }
}
