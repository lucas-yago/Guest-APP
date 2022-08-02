package com.lucasyago.guest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucasyago.guest.model.Guest
import com.lucasyago.guest.repository.GuestRepository

class PresentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listPresentGuests = MutableLiveData<List<Guest>>()
    val guests: LiveData<List<Guest>> = listPresentGuests

    fun getPresence() {
        listPresentGuests.value = repository.getPresence()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

}