package com.mikesu.vgti_sentinel_launcher.utils

import android.content.Context
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager

/**
 * Created by MikeSu on 23.02.2017.
 * www.michalsulek.pl
 *
 *
 * http://stackoverflow.com/questions/33761818/turning-on-wifi-tethering-programmatically
 */


fun setWifiApState(context: Context, enabled: Boolean): Boolean {
    try {
        val mWifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (enabled) {
            mWifiManager.isWifiEnabled = false
        }

        val conf = wifiApConfiguration
        mWifiManager.addNetwork(conf)

        return mWifiManager.javaClass
                .getMethod("setWifiApEnabled", WifiConfiguration::class.java, Boolean::class.javaPrimitiveType)
                .invoke(mWifiManager, conf, enabled) as Boolean

    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }

}

fun isWifiApOn(context: Context): Boolean {
    val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val apState = wifiManager.javaClass.getMethod("getWifiApState").invoke(wifiManager) as Int

    return apState == 13
}

private val wifiApConfiguration: WifiConfiguration
    get() {
        val conf = WifiConfiguration()
        conf.SSID = "VSGS"
        conf.preSharedKey = "golf5GTI"
        conf.hiddenSSID = true
        conf.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED)
        conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN)
        conf.allowedProtocols.set(WifiConfiguration.Protocol.WPA)
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
        return conf
    }
