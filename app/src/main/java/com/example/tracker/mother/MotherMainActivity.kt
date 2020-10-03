package com.example.tracker.mother

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.tracker.*
import com.example.tracker.pregnant.ConsultationActivity
import com.example.tracker.pregnant.GeneralActivity
import com.example.tracker.pregnant.ProfileActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_mother_main.*

class MotherMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mother_main)

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

        tracker_mother.setOnClickListener{
            startActivity(Intent(this, TrackerMotherActivity::class.java))

        }

        pain.setOnClickListener {
            startActivity(Intent(this, PainActivity::class.java))

        }

        nutrition.setOnClickListener {
            startActivity(Intent(this, NutritionActivity::class.java))

        }

        feeding.setOnClickListener{
            startActivity(Intent(this, FeedingActivity::class.java))

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
                startActivity(Intent(this, MotherMainActivity::class.java))
            }
            R.id.nav_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
            R.id.nav_consultation ->{
                startActivity(Intent(this, ConsultationActivity::class.java))
            }

            R.id.nav_profile ->{
                startActivity(Intent(this, MotherProfileActivity::class.java))
            }
            R.id.baby_profile ->{
                startActivity(Intent(this, BabyProfileActivity::class.java))

            }
            R.id.nav_settings ->{
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.nav_tracker ->{
                startActivity(Intent(this, TrackerMotherActivity::class.java))
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
