package com.example.tracker.mother

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.tracker.R
import com.example.tracker.util.NotificationUtil
import com.example.tracker.util.PrefUtil
import io.opencensus.stats.Aggregation

import kotlinx.android.synthetic.main.activity_diapers.*
import kotlinx.android.synthetic.main.chat_item_left.*
import kotlinx.android.synthetic.main.content_diapers.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlinx.android.synthetic.main.activity_diapers.toolbar as toolbar1

class DiapersActivity : AppCompatActivity() {

    companion object{
        fun setAlarm(context: Context, nowSeconds:Long, secondsRemaining:Long):Long{
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManger = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context,TimerExpireReceiver::class.java )
            val pendingIntent = PendingIntent.getBroadcast(context,0,intent,0)
            alarmManger.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context){
            val intent = Intent(context, TimerExpireReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0,intent,0)
            val alarmManger = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManger.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0,context)
        }
        val nowSeconds: Long
        get() = Calendar.getInstance().timeInMillis / 1000
    }

    enum class TimerState{
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds = 0L
    private var timerState = TimerState.Stopped

    private var secondsRemaining = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diapers)
        setSupportActionBar(toolbar)

        supportActionBar?.setIcon(R.drawable.timer)
        supportActionBar?.title ="      Timer"

        fab_start.setOnClickListener{
            v->
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        fab_pause.setOnClickListener{ v ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        fab_stop.setOnClickListener{
            v ->
            timer.cancel()
            onTimerFinished()
        }
    }

    override fun onResume(){
        super.onResume()
        initTimer()

        removeAlarm(this)
        NotificationUtil.hideTimerNotification(this)
    }

    override fun onPause() {
        super.onPause()
        if(timerState==TimerState.Running){
            timer.cancel()
            val wakeUpTime = setAlarm(this, nowSeconds, secondsRemaining)
            NotificationUtil.showTimerRunning(this, wakeUpTime)
        }
        else if (timerState == TimerState.Paused){
            NotificationUtil.showTimerPaused(this)
        }

        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, this)
        PrefUtil.setSecondsRemaining(secondsRemaining,this)
        PrefUtil.setTimerState(timerState,this)

    }
    private fun initTimer(){
        timerState = PrefUtil.getTimerState(this)
        if(timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if(timerState == TimerState.Running || timerState ==TimerState.Paused)
            PrefUtil.getSecondsRemaining(this)

        else
            timerLengthSeconds

        val alarmSetTime = PrefUtil.getAlarmSetTime(this)
        if(alarmSetTime >0)
            secondsRemaining -= nowSeconds - alarmSetTime
        if(secondsRemaining <=0)
            onTimerFinished()

        else if(timerState ==TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished(){
        timerState = TimerState.Stopped

        setNewTimerLength()
        progress_countdown.progress = 0
        PrefUtil.setSecondsRemaining(timerLengthSeconds,this)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer(){
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000){
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        text_view_countdown.text = "$minutesUntilFinished:${if(secondsStr.length == 2) secondsStr
        else "0" + secondsStr}"
        progress_countdown.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons(){
        when(timerState){
            TimerState.Running ->{
                fab_start.isEnabled = false
                fab_pause.isEnabled = true
                fab_stop.isEnabled = true
            }
            TimerState.Stopped ->{
                fab_start.isEnabled = true
                fab_pause.isEnabled = false
                fab_stop.isEnabled = false
            }
            TimerState.Paused ->{
                fab_start.isEnabled = true
                fab_pause.isEnabled = false
                fab_stop.isEnabled = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_timer,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings ->{
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

}
