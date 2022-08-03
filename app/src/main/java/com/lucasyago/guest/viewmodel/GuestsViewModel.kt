package com.lucasyago.guest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucasyago.guest.model.Guest
import com.lucasyago.guest.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listGuests = MutableLiveData<List<Guest>>()
    val guests: LiveData<List<Guest>> = listGuests


    fun getAll() {
        listGuests.value = repository.getAll()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun getByStatus(status: Int) {
        val result = repository.getByStatus(status)
        listGuests.postValue(result)
    }


}
