package com.mobile.tskpersonelteminapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.tskpersonelteminapp.utils.NotificationManager
import com.mobile.tskpersonelteminapp.data.AppPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application){
    private val notificationManager = NotificationManager(application)
    private val appPreferences = AppPreferences(application)

    val isFirstLaunch = appPreferences.isFirstLaunch()

    private val _isChatNotificationEnabled = MutableStateFlow(appPreferences.isNotificationEnabled("chat"))
    val isChatNotificationEnabled: StateFlow<Boolean> = _isChatNotificationEnabled


    init {
        if (isFirstLaunch){
            setNotificationEnabled("chat",true)
            Log.d("settingviewmodel", "Topic abone olundu")
            setFirstLaunchDone()
        }
    }
    fun setNotificationEnabled(topic: String,enabled : Boolean){
        viewModelScope.launch {
            _isChatNotificationEnabled.value = enabled//switchin daha hızlı tepki vermesi için
            notificationManager.setNotificationEnabled(topic,enabled)
            appPreferences.setNotificationEnabled(topic, enabled)
        }
    }

    fun setFirstLaunchDone(){
        appPreferences.setFirstLaunchDone()
    }

    fun isNotificationEnabled(topic: String) : Boolean{
        return appPreferences.isNotificationEnabled(topic)
    }

}