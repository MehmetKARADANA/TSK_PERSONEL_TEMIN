package com.mobile.tskpersonelteminapp.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.mobile.tskpersonelteminapp.data.FcmApi
import com.mobile.tskpersonelteminapp.data.models.ChatState
import com.mobile.tskpersonelteminapp.data.models.NotificationBody
import com.mobile.tskpersonelteminapp.data.models.SendMessageDto
import com.squareup.moshi.JsonReader.Token
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okio.IOException
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class NotificationViewModel : ViewModel() {

    var state by mutableStateOf(ChatState())
        private set

    private val api: FcmApi = Retrofit.Builder()
        .baseUrl("http:10.0.2.2:8080/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()

    init {
        viewModelScope.launch {
            Firebase.messaging.subscribeToTopic("testTopic").await()
        }
    }

    fun onRemoteTokenChange(newToken: String) {
        state = state.copy(
            remoteToken = newToken
        )
    }

    fun onSubmitToken() {
        state = state.copy(
            isEnteringToken = false
        )
    }

    fun onMessageChange(message: String) {
        state = state.copy(
            messageText = message
        )
    }

    fun sendMessage(isBroadcast: Boolean) {
        viewModelScope.launch {

            val messageDto = SendMessageDto(
                to = if (isBroadcast) null else state.remoteToken,
                notification = NotificationBody(
                    title = "NewMessage",
                    body = state.messageText
                )
            )

            try {
                if (isBroadcast) {
                    api.broadcast(messageDto)
                } else {
                    api.sendMessage(messageDto)
                }

                state = state.copy(
                    messageText = ""
                )
            } catch (e: HttpException) {
                e.printStackTrace()
//handleexception
            }catch (e : IOException){
                e.printStackTrace()
            }

        }
    }
}