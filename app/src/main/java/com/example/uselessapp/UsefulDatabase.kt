package com.example.uselessapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [com.example.uselessapp.Useful::class],version=1)
abstract class UsefulDatabase:RoomDatabase() {
    abstract fun usefulDao() : com.example.uselessapp.UsefulDao

    companion object{
        //Singleton prevents multiple instance of the database
        //opening at the same time
        @Volatile
        private var INSTANCE : com.example.uselessapp.UsefulDatabase? = null

        fun getDatabase(context: Context): com.example.uselessapp.UsefulDatabase{
            val tempDb = com.example.uselessapp.UsefulDatabase.Companion.INSTANCE
            if(tempDb!=null){
                return tempDb
            }

            //Create an instance of the database
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    com.example.uselessapp.UsefulDatabase::class.java,
                    "useful_db"
                ).build()

                com.example.uselessapp.UsefulDatabase.Companion.INSTANCE = instance
                return instance
            }
        }
    }
}