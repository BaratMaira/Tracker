package com.example.tracker.weeks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tracker.R
import kotlinx.android.synthetic.main.activity_increase_sllep.*

class IncreaseSllepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_increase_sllep)

        setupViews()
    }

    private fun setupViews(){
        clear_icon.setOnClickListener{
            startActivity(Intent(this, FirstWeekActivity::class.java))
            finish()
        }
    }
}
