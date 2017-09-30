package com.pfariasmunoz.joke;

import android.app.IntentService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;



/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class DelayedMessageService extends IntentService {


    // Use a constant to pass a message from the actiity to the service
    public static final String EXTRA_MESSAGE = "message";
    // this is for identify the notification
    public static final int NOTIFICATION_ID = 5453;


    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    // this is method is called when the service receives and intent
    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Get the text from the intent
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }

    private void showText(final String text) {

        Log.i("Delayed mierda", text);

        Intent actionIntent = new Intent(this, MainActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(intent);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String notificationChannelId = "My channel_01";
        int importantce = notificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel(notificationChannelId, "human readabke tititle" importantce);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(text)
                .setChannel("my notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] {0, 1000})
                .setAutoCancel(true)
                .setContentIntent(actionPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());


//
//        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification);



    }
}
