package com.mobile.tskpersonelteminapp.data.models

data class SendMessageDto(
    val to : String?,
    val notification : NotificationBody
)

data class NotificationBody(
    var title : String,
    val body : String
)
