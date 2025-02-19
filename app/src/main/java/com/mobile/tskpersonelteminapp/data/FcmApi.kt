package com.mobile.tskpersonelteminapp.data

import com.mobile.tskpersonelteminapp.data.models.SendMessageDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
interface FcmApi {
    @POST("/api/fcm/send")
    suspend fun sendMessage(@Body request: SendMessageDto): Response<Unit>

    @POST("/api/fcm/broadcast")
    suspend fun broadcast(@Body request: SendMessageDto): Response<Unit>
}