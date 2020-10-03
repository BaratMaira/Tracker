package com.example.tracker.pregnant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.networking.ApiFactory
import com.example.tracker.pregnant.adapter.ConsultationAdapter
import com.example.tracker.pregnant.adapter.Nutrition1Adapter
import com.example.tracker.pregnant.adapter.NutritionAdapter
import com.example.tracker.pregnant.model.*
import kotlinx.android.synthetic.main.activity_consultation.*
import kotlinx.android.synthetic.main.activity_nutrition_pregnant.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Response

class NutritionPregnantActivity : AppCompatActivity() {
    private lateinit var nutritionAdapter: NutritionAdapter
    private val  nutritions: ArrayList<Nutrition> = ArrayList()
    private lateinit var nutrition1Adapter: Nutrition1Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition_pregnant)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)


        recyclerView_filterArea.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?
        ApiFactory.getApiClient1().getNutritions()
            .enqueue(object : retrofit2.Callback<NutritionResponse>{
                override fun onFailure(call: Call<NutritionResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<NutritionResponse>,
                    response: Response<NutritionResponse>
                ) {
                    val nutritionResponse = response.body()
                    Log.d("taaag", nutritionResponse.toString())
                    nutritionAdapter = NutritionAdapter(ArrayList(nutritionResponse!!.nutritions), onClick = {
                            nutrition ->
                        Toast.makeText(this@NutritionPregnantActivity, nutrition.title.toString(), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@NutritionPregnantActivity,NutritionDetailsActivity::class.java)
                        startActivity(intent)

                    })
                    recyclerView_filterArea.adapter = nutritionAdapter
                    nutritionAdapter.notifyDataSetChanged()
                }
            })

        recyclerView_nutrition.layoutManager = GridLayoutManager(this, 2)
        ApiFactory.getApiClient1().getNutritionsNot()
            .enqueue(object : retrofit2.Callback<NutritionResponses>{
                override fun onFailure(call: Call<NutritionResponses>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<NutritionResponses>,
                    response: Response<NutritionResponses>
                ) {
                    val nutritionResponse = response.body()
                    Log.d("taaag", nutritionResponse.toString())
                    nutrition1Adapter = Nutrition1Adapter(ArrayList(nutritionResponse!!.nutritionn), onClick = {
                            nutrition ->
                        Toast.makeText(this@NutritionPregnantActivity, nutrition.title.toString(), Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this@NutritionPregnantActivity,DoctorsActivity::class.java)
//                        intent.putExtra("id", hospital.id)
//                        startActivity(intent)

                    })
                    recyclerView_nutrition.adapter = nutrition1Adapter
                    nutrition1Adapter.notifyDataSetChanged()
                }
            })
    }
}
