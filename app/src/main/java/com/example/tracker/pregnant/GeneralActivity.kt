package com.example.tracker.pregnant

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.tracker.*
import com.example.tracker.weeks.FirstWeekActivity
//import com.example.tracker.pregnant.TrackerActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_general.*

class GeneralActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)

        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)
        nav_view.bringToFront()
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.setCheckedItem(R.id.nav_home)

        tracker.setOnClickListener{
            startActivity(Intent(this, TrackerActivity::class.java))
        }

        graph.setOnClickListener {
            startActivity(Intent(this, FirstWeekActivity::class.java))
        }

        advice.setOnClickListener {
            startActivity(Intent(this, AdviceActivity::class.java))
        }

        consultation.setOnClickListener {
            startActivity(Intent(this, ConsultationActivity::class.java))
        }

        articles.setOnClickListener{
            startActivity(Intent(this, ArticlesActivity::class.java))
        }

        nutrition.setOnClickListener {
            startActivity(Intent(this, NutritionPregnantActivity::class.java))
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home ->{
                startActivity(Intent(this, GeneralActivity::class.java))
            }
            R.id.nav_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
            R.id.nav_consultation ->{
                startActivity(Intent(this, ConsultationActivity::class.java))

            }
            R.id.nav_profile ->{
                startActivity(Intent(this, ProfileActivity::class.java))

            }
            R.id.nav_settings ->{
                startActivity(Intent(this, SettingsActivity::class.java))
                finish()
            }
            R.id.nav_signout ->{
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, GenderActivity::class.java))
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
