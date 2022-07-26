package com.lucasyago.guest.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucasyago.guest.model.Guest

@Database(entities = [Guest::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract  fun guestDao(): GuestDao

    companion object {
        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

    }
}
