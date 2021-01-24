package com.mehyo.nyttopstories.ui.dbvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mehyo.nyttopstories.database.ResultDatabase
import com.mehyo.nyttopstories.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Result>>
    private val repository: Repository

    init {
        //creating Dao instance
        val resultDao = ResultDatabase.getDatabase(application).resultDao()
        repository= Repository(resultDao)
        //Read All the Result objects from Database using the Repository object
        readAllData=repository.readAllData
    }

    //add Result object to Database using the Repository object inside IO Coroutine
    fun addResult(result: Result){
        viewModelScope.launch(Dispatchers.IO){
            repository.addResult(result)
        }

    }

    //Delete Result object From Database using the Repository object inside IO Coroutine
    fun deleteResult(result: Result){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteResult(result)
        }
    }

    //Delete All the Result objects from Database using the Repository object inside IO Coroutine
    fun deleteAllResults(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllResults()

        }
    }
}