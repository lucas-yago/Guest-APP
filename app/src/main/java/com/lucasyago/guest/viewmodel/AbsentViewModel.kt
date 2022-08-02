package com.lucasyago.guest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucasyago.guest.model.Guest
import com.lucasyago.guest.repository.GuestRepository

class AbsentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listAbsentGuests = MutableLiveData<List<Guest>>()
    val guests: LiveData<List<Guest>> = listAbsentGuests


    fun getAbsent(){
        listAbsentGuests.value = repository.getAbsent()
    }
    fun delete(id: Int) {
        repository.delete(id)
    }

}