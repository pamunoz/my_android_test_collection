package com.pfariasmunoz.timertutorial.util

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import com.pfariasmunoz.timertutorial.AppConstants
import com.pfariasmunoz.timertutorial.R
import com.pfariasmunoz.timertutorial.TimerActivity
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
            startIntent.action = AppConstants.ACTION_START
            val startPendingIntent = PendingIntent.getBroadcast(context, 0,
                    startIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            // Create the actual notifications
            val nBuilder = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, true)
            nBuilder.setContentTitle("Timer Expired")
                    .setContentText("Start again?")
                    .setContentIntent(getPendingIntentWithStack(context, TimerActivity::class.java))
                    .addAction(R.drawable.ic_play, "Start", startPendingIntent)
        }

        private fun getBasicNotificationBuilder(
                context: Context, channeLId: String, playSound: Boolean)
                : NotificationCompat.Builder {
            val notificationSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val nBuilder = NotificationCompat.Builder(context, channeLId)
                    .setSmallIcon(R.drawable.ic_timer)
                    .setAutoCancel(true)
                    .setDefaults(0)
            if (playSound) nBuilder.setSound(notificationSound)
            return nBuilder
        }

        private fun <T> getPendingIntentWithStack(context: Context, javaClass: Class<T>): PendingIntent {
            val resultIntent = Intent(context, javaClass)
            // This will make that if the activity is already open, is not going to be created again
            resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(javaClass)
            // This is the activity that we want to be open
            stackBuilder.addNextIntent(resultIntent)

            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

    }
}