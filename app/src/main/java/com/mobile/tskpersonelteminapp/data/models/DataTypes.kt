package com.mobile.tskpersonelteminapp.data.models

import com.google.firebase.Timestamp


data class Announcement(
    val _id: String?="",
    val title: String?="",
    val date: String?="",
    val detail_url: String?="",
    val detail_content: String?="",
    val state: String?="",
    val created_at: Timestamp? = null,
    val updated_at: Timestamp? = null
)

data class Assurances(
    val _id: String?="",
    val title: String?="",
    val date: String?="",
    val detail_url: String?="",
    val detail_content: String?="",
    val state: String?="",
    val created_at: Timestamp? = null,
    val updated_at: Timestamp? = null
)
