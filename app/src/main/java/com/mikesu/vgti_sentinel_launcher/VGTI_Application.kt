package com.mikesu.vgti_sentinel_launcher

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

/**
 * www.michalsulek.pl
 */
class VGTI_Application : Application() {

    companion object {
        val CHECK_INTERVAL_IN_MILLIS = 10000L // it's always forced up to 60 sec, read documentation about setRepeating
        val REQUEST_CODE = 17
    }

    override fun onCreate() {
        super.onCreate()
        (applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager).setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                CHECK_INTERVAL_IN_MILLIS,
                PendingIntent.getBroadcast(
                        applicationContext,
                        REQUEST_CODE,
                        Intent(applicationContext, VGTI_Receiver::class.java),
                        PendingIntent.FLAG_UPDATE_CURRENT)
        )
    }
}
