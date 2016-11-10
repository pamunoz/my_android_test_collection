package com.pfariasmunoz.joke;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class DelayedMessageService extends IntentService {

    public static final String TAG = DelayedMessageService.class.getSimpleName();
                                                         // Use a constant to pass
    public static final String EXTRA_MESSAGE = "message";// a message from the
                                                         // activity to the service
    public DelayedMessageService() {
        // call the super constructor
        super("DelayedMessageService");
    }

    /*
      This method contains the code you want to
      run when the service receives an intent.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // get the text from the intent
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }

    // And we want to declare the service in Anadroid Manifest like Activities
    // using the <service> element. (but Android do it automatically when you decreate
    // a service).
    private void showText(final String text) {
        Log.v("DelayedMessageService", "The message is: " + text);
    }
}
