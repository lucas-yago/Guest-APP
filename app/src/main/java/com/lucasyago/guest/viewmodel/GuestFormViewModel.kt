package com.lucasyago.guest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucasyago.guest.model.Guest
import com.lucasyago.guest.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<Guest>()
    val guest: LiveData<Guest> = guestModel
    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest

    fun validateForm(guest: Guest) = !guest.name.isNullOrEmpty()

    fun save(guest: Guest) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                _saveGuest.value = "Novo convidado adicionado"
            } else {
                _saveGuest.value = "Falha"
            }
        } else {
            if (repository.update(guest)) {
                _saveGuest.value = "Convidado atualizado com sucesso"
            } else {
                _saveGuest.value = "Falha"
            }

        }
    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }

}