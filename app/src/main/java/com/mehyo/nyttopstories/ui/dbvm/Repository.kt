package com.mehyo.nyttopstories.ui.dbvm

import androidx.lifecycle.LiveData
import com.mehyo.nyttopstories.database.ResultDao
import com.mehyo.nyttopstories.model.Result

class Repository(private  val resultDao: ResultDao) {
    //Read All the Result objects from Database using the Dao object
    val readAllData: LiveData<List<Result>> = resultDao.readAllData()
    
    //add Result object to Database using the Dao object
    suspend fun addResult(result: Result){
        resultDao.addResult(result)
    }

    //Delete Result object From Database using the Dao object
    suspend fun deleteResult(result: Result){
        resultDao.deleteResult(result)
    }
    //Delete All the Result objects from Database using the Dao object
    suspend fun deleteAllResults(){
        resultDao.deleteAllResults()
    }
}