package com.lucasyago.guest.repository

import android.content.Context

class GuestRepository private constructor(context:Context){

    private val guestDataBase = GuestDataBase(context)

    //singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context:Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

}