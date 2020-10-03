package com.example.tracker.pregnant

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tracker.R
import com.example.tracker.networking.ApiFactory
import com.example.tracker.pregnant.adapter.DoctorDetailAdapter
import com.example.tracker.pregnant.adapter.DoctorDetailCardAdapter
import com.example.tracker.pregnant.model.Doctor
import com.example.tracker.pregnant.model.DoctorResponse
import kotlinx.android.synthetic.main.activity_doctor_details.*
import kotlinx.android.synthetic.main.activity_doctor_details_card.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Response

class DoctorDetailsCardActivity : AppCompatActivity() {

    private lateinit var doctorDetailCardAdapter: DoctorDetailCardAdapter
    private val  doctors: ArrayList<Doctor> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details_card)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView_detail_card.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

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
                    doctorDetailCardAdapter = DoctorDetailCardAdapter(ArrayList(doctorResponse!!.doctors), onClick = {
                            doctor ->
                        Toast.makeText(this@DoctorDetailsCardActivity, doctor.name.toString(), Toast.LENGTH_SHORT).show()
                    })
                    recyclerView_detail_card.adapter = doctorDetailCardAdapter
                    doctorDetailCardAdapter.notifyDataSetChanged()
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
