package com.mikesu.vgti_sentinel_launcher.wake_up

import android.app.IntentService
import android.content.Intent
import com.mikesu.vgti_connected_launcher.utils.changeAirplaneSetting
import com.mikesu.vgti_connected_launcher.utils.isAirplaneModeOn
import com.mikesu.vgti_sentinel_launcher.utils.isWifiApOn
import com.mikesu.vgti_sentinel_launcher.utils.setWifiApState

/**
 * www.michalsulek.pl
 */
class WakeUpService : IntentService(WakeUpService::class.java.simpleName) {

    override fun onHandleIntent(p0: Intent?) {
        if (isAirplaneModeOn(applicationContext)) {
            changeAirplaneSetting(false)
        }

        if (!isWifiApOn(applicationContext)) {
            setWifiApState(applicationContext, true)
        }
    }
}