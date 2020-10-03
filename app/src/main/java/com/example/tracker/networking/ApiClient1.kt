package com.example.tracker.networking

import com.example.tracker.pregnant.model.NutritionResponse
import com.example.tracker.pregnant.model.NutritionResponseDetail
import com.example.tracker.pregnant.model.NutritionResponses
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient1 {
    @GET("db")
    fun getNutritions(): Call<NutritionResponse>

    @GET("db")
    fun getNutritionsNot(): Call<NutritionResponses>

    @GET("db")
    fun getNutritionDetail(): Call<NutritionResponseDetail>
}