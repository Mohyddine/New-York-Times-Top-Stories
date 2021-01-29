package com.mehyo.nyttopstories.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mehyo.nyttopstories.model.Result

@Database( entities= [Result::class],version = 2,exportSchema = false)
abstract class ResultDatabase: RoomDatabase() {

    abstract fun resultDao(): ResultDao
    
    companion object{
        @Volatile
        private var INSTANCE: ResultDatabase? = null
        //creating one Instance of the database to always call the exact same Instance
        fun getDatabase(context: Context): ResultDatabase{
            val tempInstance= INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDatabase::class.java,
                    "result_database",
                ).fallbackToDestructiveMigration().build()
                INSTANCE=instance
                return instance
            }
        }
    }
}