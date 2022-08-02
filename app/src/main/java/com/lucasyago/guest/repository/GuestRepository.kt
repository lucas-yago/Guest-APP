package com.lucasyago.guest.repository

import android.content.ContentValues
import android.content.Context
import com.lucasyago.guest.constants.DataBaseConstants
import com.lucasyago.guest.model.Guest

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

    fun insert(guest: Guest): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = guest.presence

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: Guest): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = guest.presence

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun get(id: Int): Guest? {
        var guest: Guest? = null
        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            //val cursor =db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)
            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection,
                args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    guest = Guest(id, name, presence)
                }
            }
            cursor.close()
            return guest
        } catch (e: Exception) {
            return guest
        }
    }

    fun getAll(): List<Guest> {
        val list = mutableListOf<Guest>()
        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, null,
                null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(Guest(id, name, presence))
                }
            }
            cursor.close()
            return list
        } catch (e: Exception) {
            return list
        }

    }


    fun getByStatus(status: Int): List<Guest> {
        val list = mutableListOf<Guest>()
        try {
            val db = guestDataBase.readableDatabase

            val cursor =
                db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = $status", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(Guest(id, name, presence))
                }
            }
            cursor.close()
            return list
        } catch (e: Exception) {
            return list
        }

    }



}