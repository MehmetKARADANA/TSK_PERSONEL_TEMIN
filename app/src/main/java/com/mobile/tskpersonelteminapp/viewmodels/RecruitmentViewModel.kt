package com.mobile.tskpersonelteminapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.mobile.tskpersonelteminapp.data.RECRUITMENTS
import com.mobile.tskpersonelteminapp.data.models.Recruitment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecruitmentViewModel @Inject constructor(
    private val db: FirebaseFirestore
) : BaseViewModel() {

    val inProcess = mutableStateOf(false)
    val recruitments = mutableStateOf<List<Recruitment>>(emptyList())

    init {
        getRecruitment()
    }

    private fun getRecruitment() {
        inProcess.value = true

        db.collection(RECRUITMENTS).whereEqualTo(
            "state", "active"
        ).addSnapshotListener { value, error ->
            inProcess.value = false
            if (error != null) {
                handleException(error, error.message.toString())
                error.printStackTrace()
                return@addSnapshotListener
            }
            if (value != null) {
                //recruitments.value = value.toObjects<Recruitment>()
                val recruitmentsList = value.toObjects<Recruitment>()
                val sortedRecruitment = recruitmentsList.sortedBy {
                    it.created_at?.toDate()
                }
                recruitments.value = sortedRecruitment
            }
        }
    }
}