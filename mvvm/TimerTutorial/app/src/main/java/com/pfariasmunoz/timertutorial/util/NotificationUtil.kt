package com.pfariasmunoz.timertutorial.util

import android.content.Context
import android.content.Intent
import com.pfariasmunoz.timertutorial.TimerNotificationActionReceiver

class NotificationUtil {
    companion object {
        private const val CHANNEL_ID_TIMER = "menu_timer"
        private const val CHANNEL_NAME_TIMER = "Timer App Timer"
        private const val TIMER_ID = 0

        fun showTimerExpired(context: Context) {
            // We want to be able to control the timer from notifications
            val startIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            // specify actions for the intent
            startIntent.action
        }

    }
}