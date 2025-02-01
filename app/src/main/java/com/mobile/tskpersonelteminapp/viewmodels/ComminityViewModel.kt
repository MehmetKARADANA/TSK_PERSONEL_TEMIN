package com.mobile.tskpersonelteminapp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.mobile.tskpersonelteminapp.data.THEMES
import com.mobile.tskpersonelteminapp.data.TOPICS
import com.mobile.tskpersonelteminapp.data.models.Theme
import com.mobile.tskpersonelteminapp.data.models.Topic
import com.mobile.tskpersonelteminapp.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComminityViewModel @Inject constructor(
    val db: FirebaseFirestore
) : ViewModel() {

    val inProcess = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    val themes = mutableStateOf<List<Theme>>(emptyList())
    val topics = mutableStateOf<List<Topic>>(emptyList())


    init {
        getThemes()
    }

    private fun getThemes() {
        inProcess.value = true
        try {
            db.collection(THEMES).addSnapshotListener { value, error ->
                if (error != null) {
                    errorMessage.value = error.message
                }
                if (value != null) {
                    themes.value = value.toObjects<Theme>()
                }
                inProcess.value = false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            inProcess.value = false
        }
    }

    fun addTheme(themeName: String) {
        inProcess.value = true
        try {
            val id = db.collection(THEMES).document().id
            val theme = Theme(themeId = id, theme = themeName, date = Timestamp.now())
            db.collection(THEMES).document(id).set(theme).addOnCompleteListener {
                if (it.isSuccessful) {
                    inProcess.value = false
                } else {
                    errorMessage.value = it.exception?.message
                    inProcess.value = false
                }
            }
            inProcess.value = false
        } catch (e: Exception) {
            errorMessage.value = e.message
            e.printStackTrace()
            inProcess.value = false
        }
    }

    fun addTopics(topic: String, user: User, themeId: String) {
        inProcess.value = true
        try {
            val id = db.collection(THEMES).document(themeId).collection(TOPICS).document().id
            val newtopic = Topic(topicId = id, topic = topic, user = user, date = Timestamp.now())
            db.collection(THEMES)
                .document(themeId)
                .collection(TOPICS)
                .document(id)
                .set(newtopic).addOnCompleteListener {
                    if (it.isSuccessful) {
                        inProcess.value = false
                    } else {
                        errorMessage.value = it.exception?.message
                        inProcess.value = false
                    }
                }

            inProcess.value = false
        } catch (e: Exception) {
            errorMessage.value = e.message
            e.printStackTrace()
            inProcess.value = false
        }
    }

    fun getTopics(themeId: String) {
        inProcess.value = true

        db.collection(THEMES).document(themeId).collection(TOPICS)
            .addSnapshotListener { value, error ->
                inProcess.value = false
                if (error != null) {
                    errorMessage.value = error.message
                    return@addSnapshotListener
                }

                topics.value = value?.toObjects<Topic>().orEmpty()
            }

    }
}