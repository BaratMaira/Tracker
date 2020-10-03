package com.example.tracker.pregnant

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tracker.R
import com.example.tracker.networking.ApiFactory
import com.example.tracker.pregnant.adapter.DoctorDetailAdapter
import com.example.tracker.pregnant.model.Doctor
import com.example.tracker.pregnant.model.DoctorResponse
import kotlinx.android.synthetic.main.activity_doctor_details.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Response

class DoctorDetailsActivity : AppCompatActivity() {
    private lateinit var doctorDetailAdapter: DoctorDetailAdapter
    private val  doctors: ArrayList<Doctor> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView_detail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        ApiFactory.getApiClient().getDoctors()
            .enqueue(object : retrofit2.Callback<DoctorResponse>{
                override fun onFailure(call: Call<DoctorResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<DoctorResponse>,
                    response: Response<DoctorResponse>
                ) {
                    val doctorResponse = response.body()
                    Log.d("taaag", doctorResponse.toString())
                    doctorDetailAdapter = DoctorDetailAdapter(ArrayList(doctorResponse!!.doctors), onClick = {
                            doctor ->
                        Toast.makeText(this@DoctorDetailsActivity, doctor.name.toString(), Toast.LENGTH_SHORT).show()
                    })
                    recyclerView_detail.adapter = doctorDetailAdapter
                    doctorDetailAdapter.notifyDataSetChanged()
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
