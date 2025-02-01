package com.mobile.tskpersonelteminapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.mobile.tskpersonelteminapp.data.THEMES
import com.mobile.tskpersonelteminapp.data.models.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComminityViewModel @Inject constructor(
    val db : FirebaseFirestore
) : ViewModel() {

    val inProcess = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    val themes = mutableStateOf<List<Theme>>(emptyList())

    init {
        getThemes()
    }
    private fun getThemes(){
        inProcess.value=true
        try {
            db.collection(THEMES).addSnapshotListener{value,error->
                if (error != null){
                    errorMessage.value=error.message
                }
                if(value !=null){
                    themes.value=value.toObjects<Theme>()
                }
                inProcess.value=false
            }
        }catch (e : Exception){
            e.printStackTrace()
            inProcess.value=false
        }
    }

   fun  addTheme(themeName :String ){
       inProcess.value=true
       try {
           val id=db.collection(THEMES).document().id
           val theme = Theme(themeId = id,theme =themeName, date = Timestamp.now())
           db.collection(THEMES).document(id).set(theme)
           inProcess.value=false
       }catch (e :Exception){
           errorMessage.value=e.message
           e.printStackTrace()
           inProcess.value=false
       }
   }

}