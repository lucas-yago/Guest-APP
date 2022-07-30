package com.lucasyago.guest.viewmodel

import androidx.lifecycle.ViewModel
import com.lucasyago.guest.repository.GuestRepository

class GuestFormViewModel: ViewModel() {

    private val repository = GuestRepository.getInstance()


}