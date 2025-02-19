package com.mobile.tskpersonelteminapp.data.models

import com.google.gson.annotations.SerializedName


data class SendMessageDto(
    val to: String?, // Tek kullanıcı için `to=token`, broadcast için `to=null`
    val notification: NotificationBody
)

data class NotificationBody(
    val title: String,
    val body: String
)