package com.mehyo.nyttopstories.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val Base_Url= "https://api.nytimes.com/svc/topstories/v2/"
    const val API_Key= "s20j6awYA5rKmEBhsiGhxlZRlNvPjGzV"
    //retrofit singleton: Instance of the Retrofit to always call the exact same Instance
    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
    }
    //api Interface singleton: Instance of the InterFaceApi to always call the exact same Instance
    val apiService: InterFaceApi by lazy {
        retrofitBuilder
            .build()
            .create(InterFaceApi::class.java)
    }
}