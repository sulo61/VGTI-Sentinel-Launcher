package com.mikesu.vgti_sentinel_launcher.sleep

import android.app.IntentService
import android.content.Intent
import com.mikesu.vgti_connected_launcher.utils.changeAirplaneSetting
import com.mikesu.vgti_connected_launcher.utils.isAirplaneModeOn
import com.mikesu.vgti_sentinel_launcher.utils.isWifiApOn
import com.mikesu.vgti_sentinel_launcher.utils.setWifiApState

/**
 * www.michalsulek.pl
 */
class SleepService : IntentService(SleepService::class.java.simpleName) {

    override fun onHandleIntent(p0: Intent?) {
        if (!isAirplaneModeOn(applicationContext)) {
            changeAirplaneSetting(true)
        }

        if (isWifiApOn(applicationContext)) {
            setWifiApState(applicationContext, false)
        }
    }
}