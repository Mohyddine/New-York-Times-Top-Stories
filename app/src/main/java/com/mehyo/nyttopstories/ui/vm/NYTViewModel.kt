package com.mehyo.nyttopstories.ui.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehyo.nyttopstories.NYTModel
import com.mehyo.nyttopstories.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import okio.IOException

class NYTViewModel: ViewModel() {
    var mutableLiveData: MutableLiveData<NYTModel> = MutableLiveData()

    //getting Recycler List Data Observer
    fun getRecyclerListDataObserver(): MutableLiveData<NYTModel> {
        return mutableLiveData
    }
    //getting the network call response using coroutine and posting data to mutableLiveData
    fun getResults(){
        viewModelScope.launch(Dispatchers.IO) {
           try {
               val response=RetrofitBuilder.apiService.
               apiGetTopStoresResult(RetrofitBuilder.API_Key)
               if(response.isSuccessful){
                   mutableLiveData.postValue(response.body()!!)
               }
           }catch (e:IOException){
               Log.d("ViewModel","IOException "+e.message)
           }

       }
    }

}