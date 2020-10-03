package com.example.tracker.mother

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.tracker.util.NotificationUtil
import com.example.tracker.util.PrefUtil

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            AppConstants.ACTION_STOP ->{
                DiapersActivity.removeAlarm(context)
                PrefUtil.setTimerState(DiapersActivity.TimerState.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE->{
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = DiapersActivity.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PrefUtil.setSecondsRemaining(secondsRemaining, context)

                DiapersActivity.removeAlarm(context)
                PrefUtil.setTimerState(DiapersActivity.TimerState.Paused, context)
                NotificationUtil.showTimerPaused(context)
            }

            AppConstants.ACTION_RESUME ->{
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeUpTime = DiapersActivity.setAlarm(context, DiapersActivity.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(DiapersActivity.TimerState.Running, context)
                NotificationUtil.showTimerRunning(context,wakeUpTime)
            }

            AppConstants.ACTION_START ->{
                val minutesRemaining = PrefUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = DiapersActivity.setAlarm(context, DiapersActivity.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(DiapersActivity.TimerState.Running, context)
                PrefUtil.setSecondsRemaining(secondsRemaining,context)
                NotificationUtil.showTimerRunning(context,wakeUpTime)
            }
        }
    }
}
