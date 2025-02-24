package com.mobile.tskpersonelteminapp.viewmodels


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mobile.tskpersonelteminapp.data.Event

open class BaseViewModel() : ViewModel() {

    // Hata mesajını saklamak için mutableStateOf
    private val _errorMessage = mutableStateOf<Event<String>?>(null)
    val errorMessage: State<Event<String>?> = _errorMessage

    // Hata işleme fonksiyonu
    protected fun handleException(exception: Exception? = null, customMessage: String = "") {
        Log.e("BaseViewModel", "Hata: ", exception)
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMsg else customMessage
        _errorMessage.value = Event(message)
    }
}
