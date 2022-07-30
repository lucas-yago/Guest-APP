package com.lucasyago.guest.repository

class GuestRepository private constructor(){

    //singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository()
            }
            return repository
        }
    }

}