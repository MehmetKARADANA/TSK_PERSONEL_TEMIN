package com.mobile.tskpersonelteminapp.data

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)

    fun isFirstLaunch(): Boolean {
        return prefs.getBoolean("FIRST_LAUNCH", true)
    }

    fun setFirstLaunchDone() {
        prefs.edit().putBoolean("FIRST_LAUNCH", false).apply()
    }

    fun isNotificationEnabled(topic: String): Boolean {
        return prefs.getBoolean(topic, true)
    }

    fun setNotificationEnabled(topic: String, enabled: Boolean) {
        prefs.edit().putBoolean(topic, enabled).apply()
    }
}