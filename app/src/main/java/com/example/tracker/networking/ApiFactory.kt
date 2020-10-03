package com.example.tracker.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val ENDPOINT = "https://my-json-server.typicode.com/BaratMaira/mairaBarat/"
    private const val ENDPOINT1 = "https://my-json-server.typicode.com/BaratMaira/nutrition/"
    private const val ENDPOINT2 = "https://my-json-server.typicode.com/BaratMaira/alergy/"

    fun getRetrofit() =
        Retrofit.Builder().baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getApiClient(): ApiClient =
        getRetrofit().create(ApiClient::class.java)

    fun getRetrofit1() =
        Retrofit.Builder().baseUrl(ENDPOINT1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getApiClient1(): ApiClient1 =
        getRetrofit1().create(ApiClient1::class.java)

    fun getRetrofit2() =
        Retrofit.Builder().baseUrl(ENDPOINT2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getApiClient2(): ApiClient2 =
        getRetrofit2().create(ApiClient2::class.java)
}