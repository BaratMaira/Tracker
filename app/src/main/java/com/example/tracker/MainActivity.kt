package com.example.tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.tracker.pregnant.GeneralActivity
import com.example.tracker.slider.IntroSlide
import com.example.tracker.slider.IntroSliderAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Convenient Tracker",
                "The tracker helps to record and manage the development of the child during pregnancy and also after pregnancy",
                R.drawable.background1
            ),
            IntroSlide(
                "Online consultation",
                "This application helps mothers with children to conduct an online consultation with a doctor",
                R.drawable.undraw_baby_ja7a
            ),
            IntroSlide(
                "Articles",
                "The app provides you with the most essential information and facts about baby health and conditions.",
                R.drawable.undraw_mobile_browsers_lib5
            )
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sliderViewPage.adapter = introSliderAdapter
        setupIndicator()
        setCurrentIndicators(0)
        sliderViewPage.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        })

        val currentUser = FirebaseAuth.getInstance().currentUser
//        if(currentUser != null){
//            startActivity(Intent(this, GeneralActivity::class.java))
//            finish()
//        }

        buttonNext.setOnClickListener{
            if(sliderViewPage.currentItem + 1 < introSliderAdapter.itemCount){
                sliderViewPage.currentItem += 1
            } else{
                startActivity(Intent(this, GenderActivity::class.java))
                finish()
            }
        }

        textSkip.setOnClickListener {
            startActivity(Intent(this, GenderActivity::class.java))
            finish()
        }
    }

    private fun setupIndicator(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicators(index: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer.get(i) as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}
