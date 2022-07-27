package com.lucasyago.guest.ui.allGuests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllGuestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is allGuests Fragment"
    }
    val text: LiveData<String> = _text
}