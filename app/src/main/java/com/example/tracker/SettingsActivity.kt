package com.example.tracker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViews()
    }

    private fun setupViews(){
        demo_card.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/e/2PACX-1vTTTncu0AjM6ZhhchoMBqRsOUtebpEFP7SUKJW1V6DbQxRYh_09GokYBI-iZges2O9H1bdfE_PyrAzY/pub")))
        }

        get_started_card.setOnClickListener{
            startActivity(Intent(this, GetStartedActivity::class.java))
        }

        faq_card.setOnClickListener{
            startActivity(Intent(this, FaqActivity::class.java))
        }
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
