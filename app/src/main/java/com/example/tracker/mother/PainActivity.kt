package com.example.tracker.mother

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.mother.adapter.SearchAdapter
import com.example.tracker.mother.adapter.SearchRepository
import com.example.tracker.mother.adapter.ViewModel
import com.example.tracker.mother.model.Search
import com.example.tracker.mother.model.SearchResponse
import com.example.tracker.networking.ApiFactory
import kotlinx.android.synthetic.main.activity_pain.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Response

class PainActivity : AppCompatActivity() {
    private lateinit var searchAdapter: SearchAdapter
    private val  alergies1: ArrayList<Search> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pain)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViews()
    }

    private fun setupViews() {
        search_list.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        button_search.setOnClickListener{
            ApiFactory.getApiClient2().getAlergies(input_text.text.toString())
                .enqueue(object : retrofit2.Callback<SearchResponse>{
                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<SearchResponse>,
                        response: Response<SearchResponse>
                    ) {

                        val artistResponse = response.body()
                        Log.d("taaag", artistResponse.toString())
                        searchAdapter = SearchAdapter(ArrayList(artistResponse!!.alergies1))
                        search_list.adapter = searchAdapter
                        searchAdapter.notifyDataSetChanged()
                    }
                })
        }
    }

}
