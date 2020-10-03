package com.example.tracker.networking

import com.example.tracker.mother.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient2 {

    @GET("db")
    fun getAlergies(@Query("") query:String): Call<SearchResponse>
}