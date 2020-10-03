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
import com.example.tracker.pregnant.adapter.DoctorAdapter
import com.example.tracker.pregnant.model.Doctor
import com.example.tracker.pregnant.model.DoctorResponse
import kotlinx.android.synthetic.main.activity_doctors.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Response

class DoctorsActivity : AppCompatActivity() {
    private lateinit var doctorAdapter: DoctorAdapter
    private val  doctors: ArrayList<Doctor> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors)
        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView_doctors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val hId = intent.getStringExtra("id")

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
                        doctorAdapter = DoctorAdapter(ArrayList(doctorResponse!!.doctors), onClick = {
                            doctor ->
                        val intent = Intent(this@DoctorsActivity, DoctorDetailsActivity::class.java)
                            startActivity(intent)
                            finish()
                    })
                    recyclerView_doctors.adapter = doctorAdapter
                    doctorAdapter.notifyDataSetChanged()
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
