package com.mobile.tskpersonelteminapp.data.repository

import android.util.Log
import com.mobile.tskpersoneltemin.data.ANNOUNCEMENTS
import com.mobile.tskpersonelteminapp.data.MongoDbClient
import com.mobile.tskpersonelteminapp.data.models.Announcement
import com.mongodb.client.MongoCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document

class AnnouncementRepository {
    private val collection : MongoCollection<Document> = MongoDbClient.getCollection(ANNOUNCEMENTS)

    suspend fun getAllAnnouncements() : List<Announcement> = withContext(Dispatchers.IO){
       try {
           collection.find().map { document ->
               Announcement(
                   _id = document.getObjectId("_id").toString(),
                   title = document.getString("title"),
                   date = document.getString("date"),
                   detail_url = document.getString("detail_url"),
                   detail_content = document.getString("detail_content"),
                   state = document.getString("state"),
                   created_at = document.getString("created_at"),
                   updated_at = document.getString("updated_at")
               )
           }.toList()
       }catch (e : Exception) {
           e.printStackTrace()
           Log.e("MongoDB Connection", "Bağlantı hatası: ${e.message}")
           emptyList<Announcement>()
       }

    }


}