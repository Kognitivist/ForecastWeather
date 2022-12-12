package com.example.gps_wether

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat


class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locListener = MyLocationListener()

        fun checkPermissions() {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val permissions = arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                ActivityCompat.requestPermissions(this, permissions, 0)
            }

        }

        fun requestLocation() {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                10, 20f, locListener
            )
        }

        checkPermissions()
        requestLocation()

    }


    override fun onStart() {
        super.onStart()



        setContent {
            Ui()
        }
    }

}







