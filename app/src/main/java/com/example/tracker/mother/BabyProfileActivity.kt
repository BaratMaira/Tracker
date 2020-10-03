package com.example.tracker.mother

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.tracker.R
import kotlinx.android.synthetic.main.activity_baby_profile.*
import kotlinx.android.synthetic.main.toolbar.*

class BabyProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_profile)
        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViews()
    }

    private fun setupViews(){
        val intent = Intent(this, MotherMainActivity::class.java)
        intent.putExtra("name", editTextUsername.toString())
        intent.putExtra("date",editTextEmail.toString() )
        intent.putExtra("weight", editTextPsswd.toString())
        intent.putExtra("height", editTextHeight.toString())
        startActivity(intent)

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
