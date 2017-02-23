package com.mikesu.vgti_connected_launcher.utils

import android.content.Context
import android.location.LocationManager
import android.provider.Settings

fun isAirplaneModeOn(context: Context) : Boolean {
    return Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) == 1
}

fun isGpsOn(context: Context) : Boolean {
    return (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager).isProviderEnabled(LocationManager.GPS_PROVIDER)
}

fun isBluetoothOn(context: Context) : Boolean {
    return Settings.System.getInt(context.contentResolver, Settings.Global.BLUETOOTH_ON, 0) == 1
}

fun isWifiOn(context: Context) : Boolean {
    return Settings.System.getInt(context.contentResolver, Settings.Global.WIFI_ON, 0) == 1
}
