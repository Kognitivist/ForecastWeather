package com.example.gps_wether


import android.location.Location
import android.location.LocationListener



open class MyLocationListener : LocationListener{



    override fun onLocationChanged(location: Location) {
        MyLocation.setMyLocLatitude(location.latitude)
        MyLocation.setMyLocLongitude(location.longitude)
    }
}