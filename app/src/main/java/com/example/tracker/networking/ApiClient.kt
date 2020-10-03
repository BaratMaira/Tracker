package com.example.tracker.networking


import com.example.tracker.pregnant.model.ArticlesResponse
import com.example.tracker.pregnant.model.DoctorResponse
import com.example.tracker.pregnant.model.HospitalResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface ApiClient {

    @GET("db")
    fun getHospitals(): Call<HospitalResponse>

    @GET("db")
    fun getDoctors(): Call<DoctorResponse>

    @GET("db")
    fun getArticles(): Call<ArticlesResponse>
}