package com.njit.njitsafety;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AddEmergencyContacts extends AppCompatActivity {
    private ArrayList<Map<String, String>> mPeopleList;
    private SimpleAdapter mAdapter;
    private AutoCompleteTextView mTxtPhoneNo;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_emergency_contacts);
        mPeopleList = new ArrayList<Map<String, String>>();
        mTxtPhoneNo = (AutoCompleteTextView) findViewById(R.id.choose_em_contacts);
        allc=(TextView)findViewById(R.id.em_contacts);

        context=this;
        new AsyncTask<Void,Void,Void>(){

            ProgressDialog pd;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd=new ProgressDialog(AddEmergencyContacts.this);
                pd.show();
            }
String n="";
            @Override
            protected Void doInBackground(Void... params) {
                PopulatePeopleList();

                JSONArray array;

                try {
                    if (PrefUtils.getStrVal("EM", context).isEmpty())
                        array = new JSONArray();
                    else
                        array = new JSONArray(PrefUtils.getStrVal("EM", context));

                    for(int i=0;i<array.length();i++){
                        JSONObject o=(JSONObject)array.get(i);
                        n=n+(i+1)+"."+o.getString("Name")+" : "+o.getString("Cell")+"\n";

                    }
                }
                catch(JSONException r){

                }

                    return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                allc.setText(n);
pd.dismiss();
            }
        }.execute();

        mAdapter = new SimpleAdapter(AddEmergencyContacts.this, mPeopleList, R.layout.contact,
                new String[] { "Name", "Phone", "Type" }, new int[] {
                R.id.ccontName, R.id.ccontNo, R.id.ccontType });
        mTxtPhoneNo.setAdapter(mAdapter);



    }
    TextView allc;
public void addByText(View v){

    EditText n=(EditText)findViewById(R.id.choose_em_contactsntext);
    EditText m=(EditText)findViewById(R.id.choose_em_contactstext);
    JSONObject j=new JSONObject();
    JSONArray array;

    try {
        if(PrefUtils.getStrVal("EM",this).isEmpty())
    array   =new JSONArray();
        else
            array   =new JSONArray(PrefUtils.getStrVal("EM",this));


        j.put("Name",n.getText().toString());
        j.put("Cell",m.getText().toString());
        array.put(j);
        PrefUtils.addToPref("EM",array.toString(),this);


    } catch (JSONException e) {
Log.e("ERR",e.getMessage());  }
//    textView.setTextAlignment;





    new AsyncTask<Void,Void,Void>(){

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(AddEmergencyContacts.this);
            pd.show();
        }
        String n="";
        @Override
        protected Void doInBackground(Void... params) {
            // PopulatePeopleList();

            JSONArray array;

            try {
                if (PrefUtils.getStrVal("EM", context).isEmpty())
                    array = new JSONArray();
                else
                    array = new JSONArray(PrefUtils.getStrVal("EM", context));

                for(int i=0;i<array.length();i++){
                    JSONObject o=(JSONObject)array.get(i);
                    n=n+(i+1)+"."+o.getString("Name")+" : "+o.getString("Cell")+"\n";

                }
            }
            catch(JSONException r){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            allc.setText(n);
            pd.dismiss();
        }
    }.execute();

}

    public void PopulatePeopleList() {
        mPeopleList.clear();
        int i=0;
        Cursor people = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (people.moveToNext()) {
            if(i<301)i++;
            else break;
            String contactName = people.getString(people
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = people.getString(people
                    .getColumnIndex(ContactsContract.Contacts._ID));
            String hasPhone = people
                    .getString(people
                            .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

            if ((Integer.parseInt(hasPhone) > 0)){
                // You know have the number so now query it like this
                Cursor phones = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId,
                        null, null);
                while (phones.moveToNext()){
                    //store numbers and display a dialog letting the user select which.
                    String phoneNumber = phones.getString(
                            phones.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String numberType = phones.getString(phones.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.TYPE));
                    Map<String, String> NamePhoneType = new HashMap<String, String>();
                    NamePhoneType.put("Name", contactName);
                    NamePhoneType.put("Phone", phoneNumber);
                    if(numberType.equals("0"))
                        NamePhoneType.put("Type", "Work");
                    else
                    if(numberType.equals("1"))
                        NamePhoneType.put("Type", "Home");
                    else if(numberType.equals("2"))
                        NamePhoneType.put("Type",  "Mobile");
                    else
                        NamePhoneType.put("Type", "Other");
                    //Then add this map to the list.
                    mPeopleList.add(NamePhoneType);
                }
                phones.close();
            }
        }
        people.close();
        startManagingCursor(people);
    }

    public void onItemClick(AdapterView<?> av, View v, int index, long arg){
        Map<String, String> map = (Map<String, String>) av.getItemAtPosition(index);
        Iterator<String> myVeryOwnIterator = map.keySet().iterator();
        while(myVeryOwnIterator.hasNext()) {
            String key=(String)myVeryOwnIterator.next();
            String value=(String)map.get(key);
            mTxtPhoneNo.setText(value);
        }
    }

}
