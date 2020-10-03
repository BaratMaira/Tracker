package com.example.tracker.mother

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.tracker.R
import kotlinx.android.synthetic.main.toolbar.*

class FirstNutritionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_nutrition)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
