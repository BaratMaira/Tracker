package com.example.tracker.weeks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.tracker.pregnant.GeneralActivity
import com.example.tracker.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_first_week.*
import kotlinx.android.synthetic.main.activity_general.*

class FirstWeekActivity : AppCompatActivity() {
    private val firstWeek: ArrayList<WeekItem> = ArrayList()
    private val database by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_week)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)


        setupViews()
    }

    private fun setupViews(){
        firstWeek.add(WeekItem(R.drawable.w1))
        firstWeek.add(WeekItem(R.drawable.w1_1))
        firstWeek.add(WeekItem(R.drawable.w1_2))
        firstWeek.add(WeekItem(R.drawable.w1_3))

        viewPager.adapter = FirstWeekAdapter(firstWeek, viewPager)
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer =  CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.75F + r * 0.15F
        })

        viewPager.setPageTransformer(compositePageTransformer)

        cardView_instructions.setOnClickListener {
            startActivity(Intent(this, IncreaseSllepActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, GeneralActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            startActivity(Intent(this, GeneralActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}


