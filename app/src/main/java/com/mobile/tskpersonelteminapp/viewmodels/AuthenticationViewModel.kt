package com.mobile.tskpersonelteminapp.viewmodels


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.mobile.tskpersonelteminapp.data.USER
import com.mobile.tskpersonelteminapp.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    val inProcess = mutableStateOf(false)
    val userData = mutableStateOf<User?>(null)
    val signIn = mutableStateOf(false)

    init {
        val currentUser = auth.currentUser
        signIn.value = currentUser != null
        currentUser?.uid?.let {
            getUserData(it)
        }
    }

    private fun getUserData(uid: String) {
        inProcess.value = true
        try {
            db.collection(USER).document(uid).addSnapshotListener { value, error ->

                error?.printStackTrace()

                if (value != null) {
                    var user = value.toObject<User>()
                    userData.value = user
                    inProcess.value = false
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
            inProcess.value = false
        }

    }

    fun logout(){
        auth.signOut()
        userData.value=null
        signIn.value=false
        //log Logged Out
    }
}