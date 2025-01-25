package com.mobile.tskpersonelteminapp.data

import com.mobile.tskpersoneltemin.data.CONNECTION_STRING
import com.mobile.tskpersoneltemin.data.DATABASE_NAME
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document

object MongoDbClient {

    private val client by lazy {
        val settings=MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(CONNECTION_STRING))
            .build()
        MongoClients.create(settings)
    }

    private val database : MongoDatabase by lazy {
        client.getDatabase(DATABASE_NAME)
    }

    fun getCollection(collectionName : String) : MongoCollection<Document>{
        return database.getCollection(collectionName)
    }

    fun close(){
        client.close()
    }

}