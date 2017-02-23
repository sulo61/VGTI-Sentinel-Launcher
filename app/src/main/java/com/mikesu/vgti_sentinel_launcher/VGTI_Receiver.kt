package com.mikesu.vgti_sentinel_launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.mikesu.vgti_sentinel_launcher.sleep.SleepService
import com.mikesu.vgti_sentinel_launcher.wake_up.WakeUpService

/**
 * www.michalsulek.pl
 */
class VGTI_Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        context?.startService(Intent(
                context,
                if (isConnected(getBatteryStatus(context)))
                    WakeUpService::class.java
                else
                    SleepService::class.java
        ))
    }

    fun isConnected(batteryStatus: Int): Boolean {
        return batteryStatus == BatteryManager.BATTERY_PLUGGED_AC || batteryStatus == BatteryManager.BATTERY_PLUGGED_USB
    }

    fun getBatteryStatus(context: Context): Int {
        return context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
                .getIntExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_STATUS_UNKNOWN)
    }

}