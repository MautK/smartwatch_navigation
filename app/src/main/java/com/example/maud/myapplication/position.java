package com.example.maud.myapplication;

import android.location.Location;

public class position
{
    public String TAG = "FOOBAR";
    private Location mLocation = new Location("");

    public void create(){
        this.mLocation.setLatitude(0.0d);
        this.mLocation.setLongitude(0.0d);
    }

    public void setLocation(Location newLocation){
        this.mLocation = newLocation;
    }

    public Location getLocation(){
        return mLocation;
    }
}
