package com.example.tracker.mother

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.tracker.util.PrefUtil

class TimerExpireReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {



       PrefUtil.setTimerState(DiapersActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
