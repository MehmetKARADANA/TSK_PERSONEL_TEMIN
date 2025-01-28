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

data class Recruitment(
    val _id: String?="",
    val title: String?="",
    val date: String?="",
    val detail_url: String?="",
    val detail_content: String?="",
    val state: String?="",
    val created_at: Timestamp? = null,
    val updated_at: Timestamp? = null
)

data class Theme(
    val themeId: String?="",
    val theme : String="",
    val topicList : List<Topic>?= emptyList()
)

data class Topic(
    val topicId: String?="",
    val topic: String?="",
    val user : User?=null,
    val comments : List<Comment>?= emptyList()
)

data class Comment(
    val commentId : String?="",
    val user : User?=null,
    val comment : String?="",
    val date : Timestamp?=null
)

data class User(
    val userId: String?="",
    val name : String?="",
   // val city : String?=""
)