package com.mobile.tskpersonelteminapp


import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class NotificationService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //update server
    }

    override fun onMessageReceived(message: RemoteMessage) {
      /*  super.onMessageReceived(message)
        //respond to message received
       /* message.notification?.let {
            Log.d("FCM_RECEIVE", "Bildirim alındı: ${it.title} - ${it.body}")
            showNotification(it.title ?: "Başlık Yok", it.body ?: "İçerik Yok")
        }*/
        if (message.notification != null) {
            // Bildirim içeriği varsa
            Log.d("FCM_RECEIVE", "Bildirim alındı: ${message.notification?.title} - ${message.notification?.body}")
            showNotification(message.notification?.title ?: "Başlık Yok", message.notification?.body ?: "İçerik Yok")
        } else if (message.data.isNotEmpty()) {
            // Data içeriği varsa
            Log.d("FCM_RECEIVE", "FCM Data Mesajı Alındı: ${message.data}")
            val title = message.data["title"] ?: "Başlık Yok"
            val body = message.data["body"] ?: "İçerik Yok"
            showNotification(title, body)
        }*/
        super.onMessageReceived(message)

        // Tüm mesaj içeriğini detaylı loglama
        Log.d("FCM_RECEIVE", "Mesaj alındı. MessageID: ${message.messageId}")
        Log.d("FCM_RECEIVE", "Notification var mı: ${message.notification != null}")
        Log.d("FCM_RECEIVE", "Data var mı: ${message.data.isNotEmpty()}")
        Log.d("FCM_RECEIVE", "Tüm veri: ${message.data}")

        // Hiçbir koşulun atlanmaması için her durumu ayrı kontrol edelim
        if (message.notification != null) {
            Log.d("FCM_RECEIVE", "Bildirim başlığı: ${message.notification?.title}")
            Log.d("FCM_RECEIVE", "Bildirim içeriği: ${message.notification?.body}")

            try {
                showNotification(
                    message.notification?.title ?: "Başlık Yok",
                    message.notification?.body ?: "İçerik Yok"
                )
                Log.d("FCM_DEBUG", "showNotification çağrıldı (notification)")
            } catch (e: Exception) {
                Log.e("FCM_ERROR", "Notification bildirimi gösterme hatası", e)
            }
        }

        // Data mesajları için ayrı bir if bloğu (else if değil)
        if (message.data.isNotEmpty()) {
            Log.d("FCM_RECEIVE", "Data mesajı içeriği: ${message.data}")

            try {
                val title = message.data["title"]
                val body = message.data["body"]

                Log.d("FCM_DEBUG", "Data'dan alınan title: $title")
                Log.d("FCM_DEBUG", "Data'dan alınan body: $body")

                if (title != null || body != null) {
                    showNotification(
                        title ?: "Başlık Yok",
                        body ?: "İçerik Yok"
                    )
                    Log.d("FCM_DEBUG", "showNotification çağrıldı (data)")
                } else {
                    Log.d("FCM_DEBUG", "Data mesajında title veya body bulunamadı")
                }
            } catch (e: Exception) {
                Log.e("FCM_ERROR", "Data bildirimi gösterme hatası", e)
            }
        }

        // Hiçbir durum oluşmadıysa
        if (message.notification == null && message.data.isEmpty()) {
            Log.d("FCM_DEBUG", "Bildirim ve data içeriği boş, işlenecek bir şey yok")
        }
    }
    private fun showNotification(title: String, message: String) {
        val channelId = "default_channel"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 📌 Android 8+ için kanal oluştur
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, // Kanal ID'si
                "Genel Bildirimler", // Kullanıcıya görünen ad
                NotificationManager.IMPORTANCE_HIGH // Önem seviyesi (sesli/görsel uyarı)
            )
            channel.description = "Bu kanal genel bildirimler içindir."
            notificationManager.createNotificationChannel(channel) // 📌 Kanalı oluştur
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }

}