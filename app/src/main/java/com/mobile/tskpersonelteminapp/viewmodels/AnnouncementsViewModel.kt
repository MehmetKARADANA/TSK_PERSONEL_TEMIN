package com.mobile.tskpersonelteminapp.viewmodels


import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.mobile.tskpersonelteminapp.data.ANNOUNCEMENTS
import com.mobile.tskpersonelteminapp.data.models.Announcement
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnnouncementsViewModel @Inject constructor(
    private val db: FirebaseFirestore
) : BaseViewModel() {

    val inProcess = mutableStateOf(false)
    val announcements = mutableStateOf<List<Announcement>>(listOf())

    init {
        getAnnouncements()

    }

    private fun getAnnouncements() {
        inProcess.value = true

        db.collection(ANNOUNCEMENTS).where(
            Filter.equalTo("state", "active")
        ).addSnapshotListener { value, error ->
            inProcess.value = false
            if (error != null) {
                //daha sonra hata mesajları için metot yazacağım
                handleException(error,error.message.toString())
                error.printStackTrace()
                return@addSnapshotListener
            }
            if (value != null) {
                announcements.value = value.toObjects<Announcement>()
            }
        }

    }

}