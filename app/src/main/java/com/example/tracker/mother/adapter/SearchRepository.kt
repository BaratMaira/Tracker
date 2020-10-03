package com.example.tracker.mother.adapter

import com.example.tracker.mother.model.SearchResponse
import com.example.tracker.networking.ApiClient2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository(
    private val apiClient2: ApiClient2
) {
    fun loadSearch(  query: String,
        onSuccess: (SearchResponse?) -> Unit
    ) {
        apiClient2
            .getAlergies("Antihistamines")
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    onSuccess(response.body())
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {

                }
            })
    }
}