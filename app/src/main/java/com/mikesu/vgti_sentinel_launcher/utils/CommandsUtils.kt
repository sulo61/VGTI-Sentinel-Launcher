package com.mikesu.vgti_connected_launcher.utils

import android.bluetooth.BluetoothAdapter
import eu.chainfire.libsuperuser.Shell
import java.util.*

val YANOSIK_MAP_ACTIVITY = "pl.neptis.yanosik.mobi.android.YanosikMapActivity"
val YANOSIK_PACKAGE = "pl.neptis.yanosik.mobi.android"

fun changeAirplaneSetting(enabled: Boolean) {
    Shell.SU.run(object : ArrayList<String>() {
        init {
            add("settings put global airplane_mode_on " + if (enabled) "1" else "0")
            add("am broadcast -a android.intent.action.AIRPLANE_MODE --ez state " + if (enabled) "true" else "false")
        }
    })
}

fun changeGpsSetting(enabled: Boolean) {
    Shell.SU.run(
            if (enabled)
                "settings put secure location_providers_allowed gps,wifi,network"
            else
                "settings put secure location_providers_allowed ' '")
}

fun changeBluetoothSetting(enabled: Boolean) {
    if (enabled) BluetoothAdapter.getDefaultAdapter().enable() else BluetoothAdapter.getDefaultAdapter().disable()
}

fun changeWifiSetting(enabled: Boolean) {
    Shell.SU.run("svc wifi " + if (enabled) "enable" else "disable")
}


fun setBrightnessOff() {
    Shell.SU.run("settings put system screen_brightness 0")
}

fun setBrightnessOn() {
    Shell.SU.run("settings put system screen_brightness 134")
}

fun setPowerSaveGovernor() {
    Shell.SU.run("echo 'powersave' > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor")
    Shell.SU.run("echo 'powersave' > /sys/devices/system/cpu/cpu1/cpufreq/scaling_governor")
}

fun setOndemandGovernor() {
    Shell.SU.run("echo 'ondemand' > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor")
    Shell.SU.run("echo 'ondemand' > /sys/devices/system/cpu/cpu1/cpufreq/scaling_governor")
}
