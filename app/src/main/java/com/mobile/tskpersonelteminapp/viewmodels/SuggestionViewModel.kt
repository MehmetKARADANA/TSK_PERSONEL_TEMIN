package com.mobile.tskpersonelteminapp.viewmodels

import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuggestionViewModel @Inject constructor(
    private val db : FirebaseFirestore
) : BaseViewModel(){



}