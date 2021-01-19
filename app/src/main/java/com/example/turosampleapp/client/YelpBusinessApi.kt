package com.example.turosampleapp.client

import com.example.turosampleapp.model.Business
import com.example.turosampleapp.model.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface YelpBusinessApi {
    @GET("search")
    fun getBusinessSearch(@QueryMap params: Map<String, String>): Call<SearchResult>

    @GET("search")
    fun getBusinessSearchWithHeaders(@Header("Authorization") authorization: String, @QueryMap params: Map<String, String>): Call<SearchResult>

}