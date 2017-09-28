package com.pfariasmunoz.joke;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
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
    private Handler mHandler;

    // This method runs on the main thread, so here we create the handler to show a message on the ui
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        mHandler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

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
        // this post the toast to the main thread
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // getApplicationContext because a service does't have access the screen
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }
        });
        Log.v("DelayedMessageService", "The message is: " +  text);
    }
}
