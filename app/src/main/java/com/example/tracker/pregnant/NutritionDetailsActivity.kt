package com.example.tracker.pregnant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.networking.ApiFactory
import com.example.tracker.pregnant.adapter.NutritionAdapter
import com.example.tracker.pregnant.adapter.NutritionDetailsAdapter
import com.example.tracker.pregnant.model.Nutrition
import com.example.tracker.pregnant.model.NutritionResponse
import com.example.tracker.pregnant.model.NutritionResponseDetail
import kotlinx.android.synthetic.main.activity_nutrition_details.*
import kotlinx.android.synthetic.main.activity_nutrition_pregnant.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Response

class NutritionDetailsActivity : AppCompatActivity() {
    private lateinit var nutritionDetailsAdapter: NutritionDetailsAdapter
    private val  nutrition1: ArrayList<Nutrition> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition_details)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)


        recyclerView_detail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ApiFactory.getApiClient1().getNutritionDetail()
            .enqueue(object : retrofit2.Callback<NutritionResponseDetail>{
                override fun onFailure(call: Call<NutritionResponseDetail>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<NutritionResponseDetail>,
                    response: Response<NutritionResponseDetail>
                ) {
                    val nutritionResponse = response.body()
                    Log.d("taaag", nutritionResponse.toString())
                    nutritionDetailsAdapter = NutritionDetailsAdapter(ArrayList(nutritionResponse!!.nutrition1), onClick = {
                            nutrition ->
                        Toast.makeText(this@NutritionDetailsActivity, nutrition.title.toString(), Toast.LENGTH_SHORT).show()
                    })
                    recyclerView_detail.adapter = nutritionDetailsAdapter
                    nutritionDetailsAdapter.notifyDataSetChanged()
                }
            })
    }
}
