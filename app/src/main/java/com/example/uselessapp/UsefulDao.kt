package com.example.uselessapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsefulDao {
    @Insert
    suspend fun insertUseful(useful: com.example.uselessapp.Useful)

    @Query("SELECT * FROM useful")
    fun getAllUseful():LiveData<List<com.example.uselessapp.Useful>>

    @Query("SELECT * FROM useful WHERE id = :useful_id")
    fun getAUseful(useful_id: Int): com.example.uselessapp.Useful

    @Update
    suspend fun updateUseful(useful: com.example.uselessapp.Useful)

    @Delete
    suspend fun deleteUseful(useful: com.example.uselessapp.Useful)
}