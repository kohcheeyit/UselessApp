package com.example.uselessapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsefulViewModel(application: Application):AndroidViewModel(application) {
    //Maintain a references to the Repository
    private val usefulRepository:com.example.uselessapp.UsefulRepository
    //Maintain a copy of the useful record
    val allUseful:LiveData<List<com.example.uselessapp.Useful>>

    init {
        val usefulDao = com.example.uselessapp.UsefulDatabase.Companion.getDatabase(application)
            .usefulDao()

        usefulRepository= com.example.uselessapp.UsefulRepository(usefulDao)
        allUseful=usefulRepository.allUseful
    }

    //Create a coroutine function to insert data in
    //backgrounf thread
    fun insertUseful(useful:com.example.uselessapp.Useful)=viewModelScope.launch {
        usefulRepository.insertUseful(useful)
    }
}