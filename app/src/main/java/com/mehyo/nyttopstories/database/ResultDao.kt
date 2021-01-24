package com.mehyo.nyttopstories.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mehyo.nyttopstories.model.Result

@Dao
interface ResultDao {
    //add Result object to Database
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun addResult(result: Result)

    //Delete Result object From Database
    @Delete
    suspend fun deleteResult(result: Result)

    //Delete All the Result objects from Database
    @Query("Delete from result_table")
    suspend fun deleteAllResults()

    //Read All the Result objects from Database
    @Query("Select * from result_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Result>>

}