package com.njit.njitsafety;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Safety extends AppCompatActivity implements
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    LatLng latLng;
    GoogleMap mGoogleMap;
    SupportMapFragment mFragment;
    Marker currLocationMarker;


    @Override
    public void onMapReady(GoogleMap gMap) {
        mGoogleMap = gMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);

        buildGoogleApiClient();

        mGoogleApiClient.connect();

    }

    protected synchronized void buildGoogleApiClient() {
        Toast.makeText(this, "buildGoogleApiClient", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currLocationMarker = mGoogleMap.addMarker(markerOptions);
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);



    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }

    boolean ld=false;
    @Override
    public void onLocationChanged(Location location) {

        //place marker at current position
        //mGoogleMap.clear();

        if (!ld) {
            if (currLocationMarker != null) {
                currLocationMarker.remove();
            }
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currLocationMarker = mGoogleMap.addMarker(markerOptions);

            Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();

            //zoom to current position:
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
           ld=true;
            //If you only need one location, unregister the listener
            //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
        return;

    }











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety);
        Notification notification;
        NotificationManager mNotificationManager;
        long when = System.currentTimeMillis();
        RemoteViews contentView;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mFragment.getMapAsync(this);
        int n=getResources().getInteger(R.integer.safety_notification);
        contentView = new RemoteViews(getPackageName(), R.layout.safetynotification);
        Intent notsafe=new Intent(this,SafetyService.class);
        notsafe.setAction("com.njit.njitsafety.action.Safety_Unsafe");
        PendingIntent NS = PendingIntent.getService(this, 100, notsafe, 0);
        //com.njit.njitsafety.action.FOO
        Intent Call=new Intent(this,SafetyService.class);
        Call.setAction("com.njit.njitsafety.action.Call");

        PendingIntent pendingIntentCall = PendingIntent.getService(this, 1001, Call, 0);
//        contentView.setImageViewResource(R.id.callE,R.drawable.notifications_panic);
        contentView.setOnClickPendingIntent(R.id.call,pendingIntentCall);
        contentView.setOnClickPendingIntent(R.id.callE,NS);

        PendingIntent P = PendingIntent.getActivity(this, 10,new Intent(this,Safety.class), 0);



        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        findViewById(R.id.bl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(Safety.this);
                dialog.setContentView(R.layout.broadcast_location);
                dialog.show();


            }
        });

  // contentView.setInt(R.id.callE, "change", R.drawable.i);

        notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContent(contentView)
                .setContentIntent(P)
                .build();;



        notification.flags |= Notification.FLAG_NO_CLEAR;
        mNotificationManager.notify(n, notification);

    }

    public static class SBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equalsIgnoreCase("com.example.app.ACTION_")){
                Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show();
                Log.d("Action","done");


            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_safety, menu);
        return true;
    }
    public  void callEm(View v)
    {
        Toast.makeText(this,"Call Emergency",Toast.LENGTH_LONG).show();
    }
    public  void comingSoon(View v)
    {
        Toast.makeText(this,"Coming Soon.........",Toast.LENGTH_LONG).show();
    }
    public void goToAcc(View v)
    {
        Intent i=new Intent(this,Profile.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
        return;
    }

    public void getUpdates(View v)
    {
        Intent i=new Intent(this,ShowList.class);
        i.putExtra("TAG","Get Updates");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
       public void goTimeline(View v)
       {
           Intent i=new Intent(this,ShowList.class);
           i.putExtra("TAG","Timeline");
           startActivity(i);
           overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
       }
    public void goSurvey(View v){
        Intent i=new Intent(this,ShowList.class);
        i.putExtra("TAG","Surveys");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
    public void chat(View v){
        Intent i=new Intent(this,ShowList.class);
        i.putExtra("TAG","Chats");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
    public void logout(View v){
        Intent intent=new Intent(this,Welcome.class);
        PrefUtils.addToPref("LOGIN",false,this);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
    public void addEm(View v)
    {
        Intent i=new Intent(this,AddEmergencyContacts.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
        return;
    }

    boolean menuopen=true;
    public void menu(View v)
    {
        ImageButton ib=(ImageButton)v;
       if(menuopen){

           ib.setImageResource(R.drawable.menu);
           SlideAnimationUtil.slideOutToLeft(this,findViewById(R.id.menu_));
           findViewById(R.id.menu_).setVisibility(View.GONE);

           menuopen=false;
       }

        else {
           ib.setImageResource(R.drawable.close_);
           findViewById(R.id.menu_).setVisibility(View.VISIBLE);
           SlideAnimationUtil.slideInFromLeft(this,findViewById(R.id.menu_));

           menuopen=true;
       }

        return;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
   //     int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

//        return super.onOptionsItemSelected(item);
        return true;
    }
}




//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
//        // set the custom action
//        intentFilter.addAction("com.example.app.ACTION_PLAY");
//        registerReceiver(broadcastReceiver, intentFilter);
// contentView.setInt();
//Do not clear the notification