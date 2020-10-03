package com.example.tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tracker.signinMother.SignupMother
import com.example.tracker.signinPregnant.SignupPregnant
import kotlinx.android.synthetic.main.activity_gender.*

class GenderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)

        setupViews()
    }

    private fun setupViews(){

        buttonPregnant.setOnClickListener {
            startActivity(Intent(this, SignupPregnant::class.java))
            finish()
        }

        buttonMother.setOnClickListener {
            startActivity(Intent(this, SignupMother::class.java))
            finish()
        }
    }
}
