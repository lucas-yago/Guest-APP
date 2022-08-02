package com.lucasyago.guest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucasyago.guest.model.Guest
import com.lucasyago.guest.model.StatusGuest
import com.lucasyago.guest.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<Guest>>()
    val guests: LiveData<List<Guest>> = listAllGuests

    private val listStatusGuests = MutableLiveData<List<Guest>>()
    val statusGuest: LiveData<List<Guest>> = listStatusGuests

    fun getAll() {
        listAllGuests.value = repository.getAll()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun getByStatus(status: Int) {
        listStatusGuests.value = repository.getByStatus(status)
    }
}
