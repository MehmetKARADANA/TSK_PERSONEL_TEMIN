package com.mobile.tskpersonelteminapp.viewmodels




import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.data.FcmApi
import com.mobile.tskpersonelteminapp.data.models.NotificationBody
import com.mobile.tskpersonelteminapp.data.models.SendMessageDto
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class NotificationViewModel : ViewModel() {
//kalkıcak
    private val api: FcmApi = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // Local backend için
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create()

    var userToken: String? = null // Kullanıcı token'ı buraya kaydedilecek

    init {
       /* viewModelScope.launch {
            try {
                FirebaseMessaging.getInstance().subscribeToTopic("chat").await()
                Log.d("FCM", "Chat konusuna başarıyla abone olundu")
            } catch (e: Exception) {
                Log.e("FCM", "Chat konusuna abone olunamadı", e)
            }
            fetchFirebaseToken()
        }*/


    }

    private fun fetchFirebaseToken() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userToken = task.result
                    Log.d("FCM_TOKEN", "Firebase Token: $userToken")
                } else {
                    Log.e("FCM_TOKEN", "Token alınamadı", task.exception)
                }
            }
    }

//Bu fonksiyon kalkıcak
    fun sendMessage(message: String, isBroadcast: Boolean) {
        viewModelScope.launch {
            if (message.isBlank()) {
                Log.e("FCM_SEND", "Mesaj boş olamaz")
                return@launch
            }

            val messageDto = SendMessageDto(
                to = if (isBroadcast) null else userToken, // Eğer broadcast ise `to=null`
                notification = NotificationBody(
                    title = "Yeni Mesaj",
                    body = message
                )
            )

            Log.d("FCM_REQUEST", "Gönderilen JSON: ${Gson().toJson(messageDto)}")

            try {
                val response = if (isBroadcast) {
                    api.broadcast(messageDto)
                } else {
                    api.sendMessage(messageDto)
                }
                if (response.isSuccessful) {
                    Log.d("FCM_SEND", "Bildirim başarıyla gönderildi")
                } else {
                    Log.e("FCM_SEND", "Bildirim gönderme başarısız: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("FCM_SEND", "Hata oluştu", e)
            }
        }
    }
}