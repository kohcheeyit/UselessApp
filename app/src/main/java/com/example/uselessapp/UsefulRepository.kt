package com.example.uselessapp

import androidx.lifecycle.LiveData

class UsefulRepository (private val usefulDao: com.example.uselessapp.UsefulDao){
    //Maintains a copy of the LiveData
    val allUseful:LiveData<List<com.example.uselessapp.Useful>> = usefulDao.getAllUseful()

    //A function to insert useful record
    suspend fun insertUseful(useful:com.example.uselessapp.Useful){
        usefulDao.insertUseful(useful)
    }
}