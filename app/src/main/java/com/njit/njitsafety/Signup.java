package com.njit.njitsafety;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */




    //Define Variables

    public String acc_ids[]={
            "psl9",
            "avd37",
            "sr856",
            "bd248",
            "mh449"
    };
    EditText name,ucid,age,pass;
    RadioButton male,female;
    String ucid_v="",name_v="";
    int age_v=0;
    boolean female_checked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
     //   mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.

        name=(EditText)findViewById(R.id.ent_name);
        ucid=(EditText)findViewById(R.id.ent_ucid);
        age=(EditText)findViewById(R.id.ent_age);
        pass=(EditText)findViewById(R.id.ent_pass);
        female=(RadioButton)findViewById(R.id.ent_gf);
        male=(RadioButton)findViewById(R.id.ent_gm);


//        name.setText( getArguments().getString(Name));
//        ucid.setText( getArguments().getString(UCID));
//        age.setText( Integer.toString(getArguments().getInt(Age)));





final Context c=this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String u=ucid.getText().toString();
                try {
                    int a=Integer.parseInt(age.getText().toString());

                    if ((female.isChecked()||male.isChecked())&&a>16&&!name.getText().toString().isEmpty()&&(u.equals("psl9")||u.equals("avd37")||u.equals("sr856")||u.equals("bd248")||u.equals( "mh449")||u.equals("adn24"))&&pass.getText().toString().equals("admin")) {
                        PrefUtils.addToPref("NAME",name.getText().toString(),c);
                        PrefUtils.addToPref("UCID",u,c);
                        PrefUtils.addToPref("AGE",a,c);
                        PrefUtils.addToPref("PASS",pass.getText().toString(),c);

                        if(female.isChecked()) {
                            PrefUtils.addToPref("GENDER","F",c);

                        }
                        else{
                            PrefUtils.addToPref("GENDER","M",c);

                        }
                    }
                else{
                        Toast.makeText(c,"NOT VALID",Toast.LENGTH_LONG).show();

                    }
                } catch (NumberFormatException e) {


                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class SignUP1 extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private final String Age= "AGE";
//        private final String Name= "NAME";
//        private final String UCID= "UCID";
//        private final String isFemale= "ISFEMALE";
//
//        public SignUP1() {
//        }
//
//        public SignUP1 getthis()
//        {
//            return this;
//        }
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static SignUP1 newInstance(int age,String name,String ucid,boolean isfemale) {
//            final String Age= "AGE";
//             final String Name= "NAME";final String UCID= "UCID";
//
//          final String isFemale= "ISFEMALE";
//            SignUP1 fragment = new SignUP1();
//            Bundle args = new Bundle();
//            args.putInt(Age, age);
//            args.putString(Name,name);
//            args.putString(UCID,ucid);
//            args.putBoolean(isFemale,isfemale);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.signup1, container, false);
//
////            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
////            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//
//
//            return rootView;
//        }
//    }
//
//    /**
//     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        public void setUcid_v(String ucid_v) {
//            this.ucid_v = ucid_v;
//        }
//
//        public void setName_v(String name_v) {
//            this.name_v = name_v;
//        }
//
//        public void setAge_v(int age_v) {
//            this.age_v = age_v;
//        }
//
//        public void setFemale_checked(boolean female_checked) {
//            this.female_checked = female_checked;
//        }
//
//
//        @Override
//        public Fragment getItem(int position) {
//            // getItem is called to instantiate the fragment for the given page.
//            // Return a SignUP1 (defined as a static inner class below).
//          return   SignUP1.newInstance(age_v,name_v,ucid_v,female_checked);
//        }
//
//        public String getUcid_v() {
//            return ucid_v;
//        }
//
//        @Override
//        public int getCount() {
//            // Show 3 total pages.
//            return 3;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
//            return null;
//        }
//    }
}
