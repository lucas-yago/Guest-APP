package com.lucasyago.guest.repository

import androidx.room.*
import com.lucasyago.guest.model.Guest

@Dao
interface GuestDao {

    @Insert
    fun insert(guest: Guest): Long

    @Update
    fun update(guest: Guest): Int

    @Delete
    fun delete(guest: Guest)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int): Guest

    @Query("SELECT * FROM Guest")
    fun getAll(): List<Guest>

    @Query("SELECT * FROM guest WHERE presence = :status")
    fun getByStatus(status: Int): List<Guest>


}