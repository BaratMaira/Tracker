package com.example.tracker.pregnant

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tracker.R
import com.example.tracker.networking.ApiFactory
import com.example.tracker.pregnant.adapter.ConsultationAdapter
import com.example.tracker.pregnant.model.Hospital
import com.example.tracker.pregnant.model.HospitalResponse
import kotlinx.android.synthetic.main.activity_consultation.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Response

class ConsultationActivity : AppCompatActivity() {
    private lateinit var consultationAdapter: ConsultationAdapter
    private val  hospital: ArrayList<Hospital> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)
        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView_consultation.layoutManager = GridLayoutManager(this, 2)
        ApiFactory.getApiClient().getHospitals()
            .enqueue(object : retrofit2.Callback<HospitalResponse>{
                override fun onFailure(call: Call<HospitalResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<HospitalResponse>,
                    response: Response<HospitalResponse>
                ) {
                    val hospitalResponse = response.body()
                    Log.d("taaag", hospitalResponse.toString())
                    consultationAdapter = ConsultationAdapter(ArrayList(hospitalResponse!!.hospitals), onClick = {
                            hospital ->
                        Toast.makeText(this@ConsultationActivity, hospital.strName.toString(), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@ConsultationActivity,DoctorsActivity::class.java)
                        intent.putExtra("id", hospital.id)
                        startActivity(intent)
                        finish()

                    })
                    recyclerView_consultation.adapter = consultationAdapter
                    consultationAdapter.notifyDataSetChanged()
                }
            })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
