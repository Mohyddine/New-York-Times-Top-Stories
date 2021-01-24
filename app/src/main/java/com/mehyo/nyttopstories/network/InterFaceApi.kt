package com.mehyo.nyttopstories.network

import com.mehyo.nyttopstories.NYTModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InterFaceApi {
    //api interface using suspend function for network calls
    @GET("home.json")
   suspend fun apiGetTopStoresResult(
        @Query("api-key")
        api_key: String?
    ): Response<NYTModel>
}