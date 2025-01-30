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
    private val db : FirebaseFirestore
) : ViewModel(){

    val inProcess = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val recruitments = mutableStateOf<List<Recruitment>>(emptyList())

    init {
        getRecruitment()
    }

    private fun getRecruitment(){
        inProcess.value=true
        try {
            db.collection(RECRUITMENTS).where(
                Filter.equalTo("state","active")
            ).addSnapshotListener{value ,error ->
                if(error != null){
                    errorMessage.value=error.message
                    error.printStackTrace()
                }
                if(value !=null){
                    recruitments.value=value.toObjects<Recruitment>()
                }
                inProcess.value=false
            }
        }catch (e : Exception){
            e.printStackTrace()
            inProcess.value=false
        }
    }
}