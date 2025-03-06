package com.mobile.tskpersonelteminapp.utils

import android.content.Context
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.mobile.tskpersonelteminapp.data.AppPreferences
import kotlinx.coroutines.tasks.await

class NotificationManager(private val context: Context) {
    private val prefs = AppPreferences(context)

    suspend fun setNotificationEnabled(topic: String, enabled: Boolean) {
        prefs.setNotificationEnabled(topic, enabled)

        if (enabled) {
            subscribeToTopic(topic)
        } else {
            unsubscribeFromTopic(topic)
        }
    }

    private suspend fun subscribeToTopic(topic: String) {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic(topic).await()
            Log.d("FCM", "$topic konusuna başarıyla abone olundu.")
        } catch (e: Exception) {
            Log.e("FCM", "$topic konusuna abone olunamadı.", e)
        }
    }

    private suspend fun unsubscribeFromTopic(topic: String) {
        try {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic).await()
            Log.d("FCM", "$topic konusundan çıkıldı.")
        } catch (e: Exception) {
            Log.e("FCM", "$topic konusundan çıkılamadı.", e)
        }
    }
}
