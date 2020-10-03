package com.example.tracker.mother

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.tracker.R
import kotlinx.android.synthetic.main.activity_mother_main.*
import kotlinx.android.synthetic.main.activity_tracker_mother.*
import kotlinx.android.synthetic.main.activity_tracker_mother.feeding
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.toolbar

class TrackerMotherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker_mother)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViews()
    }

    private fun setupViews(){
        feeding.setOnClickListener{
            startActivity(Intent(this, TrackerFeedingActivity::class.java))
        }
        diapers.setOnClickListener{
            startActivity(Intent(this, DiapersActivity::class.java))
        }
        health.setOnClickListener{
            startActivity(Intent(this, HealthActivity::class.java))
        }
//        pain_baby.setOnClickListener{
//            startActivity(Intent(this, PainActivity::class.java))
//        }
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
