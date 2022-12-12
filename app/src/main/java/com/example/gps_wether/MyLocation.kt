package com.example.gps_wether

open class MyLocation {
    companion object {
        var myLocLatitude:Double = 56.702
        var myLocLongitude:Double = 38.746

        @JvmName("getMyLocLatitude1")
        fun getMyLocLatitude():Double{
            return myLocLatitude
        }
        @JvmName("getMyLocLongitude1")
        fun getMyLocLongitude():Double{
            return myLocLongitude
        }

        @JvmName("setMyLoc1")
        fun setMyLocLatitude (Latitude: Double){
            myLocLatitude = Latitude
        }
        @JvmName("setMyLoc2")
        fun setMyLocLongitude (Longitude: Double){
            myLocLongitude = Longitude
        }
    }





}