package com.example.tracker.mother

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tracker.R
import kotlinx.android.synthetic.main.toolbar.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Settings"
    }
}
