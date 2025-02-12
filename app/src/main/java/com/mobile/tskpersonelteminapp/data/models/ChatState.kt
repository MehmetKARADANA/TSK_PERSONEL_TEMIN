package com.mobile.tskpersonelteminapp.data.models

data class ChatState(
    val isEnteringToken : Boolean = true,
    val remoteToken: String ="",
    val messageText : String = ""
)
