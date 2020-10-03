package com.example.tracker.mother.adapter

import com.example.tracker.mother.model.Search
import com.example.tracker.mother.model.SearchResponse
import com.example.tracker.networking.ApiFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageLoader(
    private val onSuccess: (List<Search>) -> Unit
) {

    fun loadSearch(
        query: String
    ) {
        ApiFactory.getApiClient2()
            .getAlergies(query)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    response.body()?.alergies1?.let {
                        onSuccess(it)
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {

                }
            })
    }
}