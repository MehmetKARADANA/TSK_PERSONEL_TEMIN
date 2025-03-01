package com.mobile.tskpersonelteminapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.FirebaseFirestore
import com.mobile.tskpersonelteminapp.data.SUGGESTION
import com.mobile.tskpersonelteminapp.data.models.Suggestion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuggestionViewModel @Inject constructor(
    private val db : FirebaseFirestore,
   // application: Application
) : BaseViewModel(){

    val inProcess = mutableStateOf(false)
  //  val context = application
    fun addSuggestion(sugges : String,email : String){
        inProcess.value = true
        if(sugges.isEmpty()){
            handleException(customMessage = "Öneri boş bırakılamaz!")
            inProcess.value=false
            return
        }

        val suggestion=Suggestion(
            suggestion = sugges,
            email=email
        )

        suggestion.suggestion?.let {
            db.collection(SUGGESTION).add(suggestion).addOnCompleteListener {
                inProcess.value=false

                it.exception?.let { handleException(it,it.message.toString()) }

            }
        }

    }


}