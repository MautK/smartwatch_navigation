package com.example.maud.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private LocationManager mLocationManager;
    private String TAG = "FOOBAR";
    private Location mLocation = new Location("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(this);

        mLocation.setLatitude(0.0d);
        mLocation.setLongitude(0.0d);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1,1, mLocationListener);

        final Button getCoordsButton = findViewById(R.id.coords_button);


        getCoordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = mLocation.toString();
                int duration = Toast.LENGTH_SHORT;

                Log.d(TAG, "onClick: " + mLocation.toString());
                Toast.makeText(context, text, duration).show();
            }
        });

    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mLocation = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public void checkPermission(Activity thisActivity) {
        if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(thisActivity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            );
        } else {
            //what to take instead of mMap?
            //mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                    //give access to location
                } else {
                    //no permission :(
                }
                return;
            }
        }
    }
}
