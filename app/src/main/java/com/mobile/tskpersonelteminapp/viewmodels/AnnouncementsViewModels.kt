package com.mobile.tskpersonelteminapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.tskpersonelteminapp.data.models.Announcement
import com.mobile.tskpersonelteminapp.data.repository.AnnouncementRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnnouncementsViewModels(
    private val repository: AnnouncementRepository
) : ViewModel() {

    private val _announcements = MutableStateFlow<List<Announcement>>(emptyList())
    val announcements : StateFlow<List<Announcement>> get() = _announcements

    val isLoading = mutableStateOf(false)

    val errorMessage = mutableStateOf<String?>(null)

    init {
        getAnnouncements()
    }

    fun getAnnouncements(){

        viewModelScope.launch {
            isLoading.value=true
            try {
                val result = repository.getAllAnnouncements()
                _announcements.value=result
                errorMessage.value=null
            }catch (e : Exception){
                errorMessage.value=e.message
            }finally {
                isLoading.value=false
            }
        }

    }

}