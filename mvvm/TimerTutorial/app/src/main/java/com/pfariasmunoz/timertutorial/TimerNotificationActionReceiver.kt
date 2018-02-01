package com.pfariasmunoz.timertutorial

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pfariasmunoz.timertutorial.util.PrefUtil

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            AppConstants.ACTION_STOP -> {
                TimerActivity.removeAlarm(context)
                PrefUtil.setTimerState(TimerActivity.TimerState.STOPPED, context)
            }
        }
    }
}
