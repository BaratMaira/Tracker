package com.example.tracker.pregnant

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.example.tracker.R
import kotlinx.android.synthetic.main.activity_general.*
import kotlinx.android.synthetic.main.activity_general.toolbar

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
            setResult(Activity.RESULT_CANCELED)
        }
    }
}
