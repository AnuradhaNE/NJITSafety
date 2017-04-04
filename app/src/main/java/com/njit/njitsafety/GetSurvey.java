package com.njit.njitsafety;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Anuradha on 03-04-2017.
 */

public class GetSurvey extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_GET_Survey = "GET_Survey";

    // TODO: Rename parameters

    public GetSurvey() {
        super("GetSurveys");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startGetSurveys(Context context) {
        Intent intent = new Intent(context, GetSurvey.class);
        intent.setAction(ACTION_GET_Survey);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_Survey.equals(action)) {

                try {
                    String res=   getSurveys();
                    //Log.d("res",res);
                    JSONArray Surveys=new JSONArray(res);
                    Log.d("res",res+Surveys.length());

                    // InternalFileUtils.writetoFile(this,res,this.getResources().getString(R.string.SurveyFile));
                    Log.d("TAGGG","\n"+InternalFileUtils.readFromFile(this,this.getResources().getString(R.string.surveyFile)));

                } catch (IOException e) {
                    Log.e("IOEXC","onHandleIntent(Intent intent)"+e.getMessage());
                } catch (URISyntaxException e) {
                    Log.e("URI syntax","onHandleIntent(Intent intent)"+e.getMessage());
                }
                catch (JSONException e) {
                    Log.e("JSON exc","onHandleIntent(Intent intent)"+e.getMessage());

                }
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    public static String getSurveys() throws IOException, URISyntaxException {
        // TODO: Handle action Foo
        String link="https://web.njit.edu/~adn24/njitsafety/get_Survey.php";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("ucid","adn24"));

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(link);
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        InputStream is =entity.getContent();
        Log.d("TAG",is.toString());
        BufferedReader reader = new BufferedReader
                (new InputStreamReader(is,"iso-8859-1"),8);
        String line="";
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
            Log.d("TAG2",sb.toString());

        }
        is.close();
        String  result = sb.toString();
        Log.e("RES", "connection success "+result);
        return result;
    }

    public void writeSurveys()
    {
        return;
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */

}