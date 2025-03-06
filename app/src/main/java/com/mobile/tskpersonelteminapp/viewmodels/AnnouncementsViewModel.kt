package com.mobile.tskpersonelteminapp.viewmodels


import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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

        /*where(
            Filter.equalTo("state", "active")
        )*/
        db.collection(ANNOUNCEMENTS).whereEqualTo("state", "active")
            .addSnapshotListener { value, error ->
                inProcess.value = false
                if (error != null) {
                    handleException(error, error.message.toString())
                    error.printStackTrace()
                    return@addSnapshotListener
                }
                if (value != null) {
                    val announcementsList = value.toObjects<Announcement>()
                    val sortedAnnouncements = announcementsList.sortedBy {
                        it.created_at?.toDate() // "created" Timestamp'ini Date'e çevirerek sıralıyoruz
                    }
                    announcements.value = sortedAnnouncements
                }
            }

    }

}