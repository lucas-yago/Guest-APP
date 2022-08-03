package com.lucasyago.guest.repository

import android.content.Context
import com.lucasyago.guest.model.Guest

class GuestRepository (context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDao()

    fun insert(guest: Guest): Boolean {
        return guestDataBase.insert(guest) > 0
    }

    fun update(guest: Guest): Boolean {
        return guestDataBase.update(guest) > 0
    }

    fun delete(id: Int) {
        val guest = get(id)
        guestDataBase.delete(guest)
    }

    fun get(id: Int): Guest {
        return guestDataBase.get(id)
    }

    fun getAll(): List<Guest> {
        return guestDataBase.getAll()
    }

    fun getByStatus(status: Int): List<Guest> {
       return guestDataBase.getByStatus(status)
    }


}