package com.lucasyago.guest.repository

import android.content.ContentValues
import android.content.Context
import androidx.core.content.contentValuesOf
import com.lucasyago.guest.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    //singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put("presence", presence)
            values.put("name", guest.name)

            db.insert("Guest", null, values)
            true
        } catch (e: Exception) {
            false
        }
    }


}