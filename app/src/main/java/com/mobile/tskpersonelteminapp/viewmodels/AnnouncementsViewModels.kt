package com.mobile.tskpersonelteminapp.viewmodels


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.mobile.tskpersoneltemin.data.ANNOUNCEMENTS
import com.mobile.tskpersonelteminapp.data.models.Announcement
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnnouncementsViewModels @Inject constructor(
    val db: FirebaseFirestore
) : ViewModel() {

    val inProcess = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val announcements = mutableStateOf<List<Announcement>>(listOf())

    init {
        getAnnouncements()
    }
    fun getAnnouncements() {
        inProcess.value = true
        try {
            db.collection(ANNOUNCEMENTS).where(
                Filter.equalTo("state", "active")
            ).addSnapshotListener { value, error ->
                if (error != null) {
                    //daha sonra hata mesajları için metot yazacağım
                    errorMessage.value = error.message
                    println(errorMessage.value)
                    error.printStackTrace()
                }
                if (value != null) {
                    announcements.value = value.toObjects<Announcement>()

                }
                inProcess.value = false
            }

        } catch (e: Exception) {
            e.printStackTrace()
            inProcess.value = false
        }
    }

}