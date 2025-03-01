package com.mobile.tskpersonelteminapp.viewmodels


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.mobile.tskpersonelteminapp.data.USERS
import com.mobile.tskpersonelteminapp.data.models.User
import com.mobile.tskpersonelteminapp.utils.isShortOrLong
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BaseViewModel() {

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

        db.collection(USERS).document(uid).addSnapshotListener { value, error ->
            inProcess.value = false
            error?.let {
                handleException(error,error.message.toString())
                it.printStackTrace()
                return@addSnapshotListener
            }
            if (value != null) {
                var user = value.toObject<User>()
                userData.value = user
            }

        }


    }

    fun signUp(name: String, email: String, password: String) {
        inProcess.value = true
        if (name.isEmpty() or email.isEmpty() or password.isEmpty()) {
            //handle exception ("Alanları doldurun")
            handleException(customMessage = "Alanları Doldurun.")
            inProcess.value = false
            return
        }

        if(isShortOrLong(password)){
            handleException(customMessage = "Şifreniz 6 karakterden kısa olamaz.")
            inProcess.value = false
            return
        }

        db.collection(USERS).where(
            Filter.or(
                Filter.equalTo("name", name),
                Filter.equalTo("email", email)
            )
        ).get().addOnSuccessListener {
            if (it.isEmpty) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        signIn.value = true
                        Log.d("SignUp", "signUp: User Logged In")
                        createProfile(name, email)
                        inProcess.value = false
                        handleException(customMessage = "Kayıt Olundu.")
                    } else {
                        //handle exception signUpFailed
                        handleException(it.exception,it.exception?.message.toString())
                        inProcess.value = false
                    }
                }

            } else {
                //handle exception aynı  kullanıcı var
                handleException(customMessage = "Aynı bilgilerde kullanıcı bulunuyor.")
                inProcess.value = false
            }
        }


    }

    fun login(email: String, password: String) {
        inProcess.value = true
        if (email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = "Alanları Doldurun")
            inProcess.value=false
            return
        }

        if(isShortOrLong(password)){
            handleException(customMessage = "Şifreniz 6 karakterden kısa olamaz.")
            inProcess.value = false
            return
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                signIn.value = true
                inProcess.value = false
                auth.currentUser?.uid?.let {
                    getUserData(it)
                }
            } else {
                handleException(it.exception,"Bilgilerinizi kontrol edin.")
                //HandleException login failed
                inProcess.value = false
            }
        }


    }

    private fun createProfile(name: String, email: String) {
        var uid = auth.currentUser?.uid
        val userData = User(
            userId = uid,
            name = name,
            email = email
        )

        uid?.let {
            inProcess.value = true
            db.collection(USERS).document(uid).set(userData).addOnCompleteListener {
                inProcess.value = false
                   it.exception?.let { handleException(it,it.message.toString()) }
            }
            getUserData(uid)
        }
    }

    fun logout() {
        auth.signOut()
        userData.value = null
        signIn.value = false
        //log Logged Out
    }
}