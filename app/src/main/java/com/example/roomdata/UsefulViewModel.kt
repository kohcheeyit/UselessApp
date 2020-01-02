package com.example.roomdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsefulViewModel(application: Application):AndroidViewModel(application) {
    //Maintain a references to the Repository
    private val usefulRepository:UsefulRepository
    //Maintain a copy of the useful record
    val allUseful:LiveData<List<Useful>>

    init {
        val usefulDao = UsefulDatabase
            .getDatabase(application)
            .usefulDao()

        usefulRepository= UsefulRepository(usefulDao)
        allUseful=usefulRepository.allUseful
    }

    //Create a coroutine function to insert data in
    //backgrounf thread
    fun insertUseful(useful:Useful)=viewModelScope.launch {
        usefulRepository.insertUseful(useful)
    }
}