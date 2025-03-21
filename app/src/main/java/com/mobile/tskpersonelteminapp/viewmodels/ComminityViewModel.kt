package com.mobile.tskpersonelteminapp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObjects
import com.mobile.tskpersonelteminapp.data.COMMENTS
import com.mobile.tskpersonelteminapp.data.THEMES
import com.mobile.tskpersonelteminapp.data.TOPICS
import com.mobile.tskpersonelteminapp.data.models.Comment
import com.mobile.tskpersonelteminapp.data.models.Theme
import com.mobile.tskpersonelteminapp.data.models.Topic
import com.mobile.tskpersonelteminapp.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComminityViewModel @Inject constructor(
    val db: FirebaseFirestore
) : BaseViewModel() {

    val inProcess = mutableStateOf(false)

    val themes = mutableStateOf<List<Theme>>(emptyList())
    val topics = mutableStateOf<List<Topic>>(emptyList())
    val comments = mutableStateOf<List<Comment>>(emptyList())


    init {
        getThemes()
    }

    private fun getThemes() {
        inProcess.value = true
        db.collection(THEMES).addSnapshotListener { value, error ->
                inProcess.value = false
                if (error != null) {
                    handleException(error,error.message.toString())
                    return@addSnapshotListener
                }
                if (value != null) {
                    themes.value = value.toObjects<Theme>()
                }
            }
    }

    fun addTheme(themeName: String) {
        inProcess.value = true

            val id = db.collection(THEMES).document().id
            val theme = Theme(themeId = id, theme = themeName, date = Timestamp.now())
            db.collection(THEMES).document(id).set(theme).addOnCompleteListener {
                inProcess.value = false
                it.exception?.let {
                    handleException(it,it.message.toString())
                }
            }

    }

    fun addTopics(topic: String, user: User, themeId: String) {
        inProcess.value = true

            val id = db.collection(THEMES).document(themeId).collection(TOPICS).document().id
            val newtopic = Topic(topicId = id, topic = topic, user = user, date = Timestamp.now())
            db.collection(THEMES)
                .document(themeId)
                .collection(TOPICS)
                .document(id)
                .set(newtopic).addOnCompleteListener {
                    inProcess.value = false
                    it.exception?.let {
                        handleException(it,it.message.toString())
                      }
                }

    }

    fun getTopics(themeId: String) {
        inProcess.value = true


        db.collection(THEMES).document(themeId).collection(TOPICS).orderBy("pinned", Query.Direction.DESCENDING)/*.orderBy("date", Query.Direction.DESCENDING)*/
            .addSnapshotListener { value, error ->
                inProcess.value = false
                if (error != null) {
                    handleException(error,error.message.toString())
                    return@addSnapshotListener
                }
                topics.value = value?.toObjects<Topic>().orEmpty()
            }

    }

    fun addComments(comment: String, themeId: String, topicId: String, user: User) {
        inProcess.value = true

            val id = db.collection(THEMES).document(themeId).collection(TOPICS).document(topicId)
                .collection(
                    COMMENTS
                ).document().id
            val newComment =
                Comment(commentId = id, comment = comment, user = user, date = Timestamp.now())
            db.collection(THEMES).document(themeId).collection(TOPICS).document(topicId).collection(
                COMMENTS).document(id).set(newComment).addOnCompleteListener {
                  inProcess.value=false
                it.exception?.let {
                    handleException(it,"Cevap eklenemedi.")
                }
            }

    }

    fun getComments(themeId: String,topicId: String) {
        inProcess.value=true
        db.collection(THEMES).document(themeId).collection(TOPICS).document(topicId).collection(
            COMMENTS).addSnapshotListener{value,error->
                inProcess.value=false
            error?.let {
                handleException(it,it.message.toString())
                return@addSnapshotListener
            }
            comments.value=value?.toObjects<Comment>().orEmpty()
        }
    }
}