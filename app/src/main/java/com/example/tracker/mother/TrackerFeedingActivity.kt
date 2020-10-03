package com.example.tracker.mother

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_tracker_feeding.*
import android.R
import android.app.Activity
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class TrackerFeedingActivity : AppCompatActivity() {
    companion object {
        val START_TIME_IN_MILLIS = 600000;
    }

    private var mTimerRunning: Boolean = false
    var mTimeLeftInMillis = START_TIME_IN_MILLIS
    private lateinit var mCountDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tracker.R.layout.activity_tracker_feeding)
        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonStart.setOnClickListener {
            if(mTimerRunning){
                pauseTimer()
            }
            else{
                startTime()
            }
        }

        buttonReset.setOnClickListener {
            resetTimer()
        }

        updateCountDownText()
    }

    private fun startTime(){
         mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis.toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished.toInt()
                updateCountDownText()
            }

            override fun onFinish() {
                mTimerRunning = false
                buttonStart.setText("Start")
                buttonStart.setVisibility(View.INVISIBLE)
                buttonReset.setVisibility(View.VISIBLE)
            }
        }.start()

        mTimerRunning = true
        buttonStart.setText("pause")
        buttonReset.setVisibility(View.INVISIBLE)

    }

    private fun pauseTimer(){
        mCountDownTimer.cancel()
        mTimerRunning = false
        buttonStart.setText("Start")
        buttonReset.setVisibility(View.VISIBLE)
    }

    private fun resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS
        updateCountDownText()
        buttonReset.setVisibility(View.INVISIBLE)
        buttonStart.setVisibility(View.VISIBLE)
    }

    private fun updateCountDownText(){
        val minutes = (mTimeLeftInMillis / 1000) / 60
        val seconds = (mTimeLeftInMillis / 1000) % 60

        val timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds)

        text_view_countdown.setText(timeLeftFormatted)
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

