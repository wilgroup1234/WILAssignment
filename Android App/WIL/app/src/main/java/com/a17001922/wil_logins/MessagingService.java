package com.a17001922.wil_logins;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

//This class manages the Notifications Messaging services functionality
public class MessagingService extends FirebaseMessagingService
{
    //_____________Declarations_________________
    private static final String TAG = "FCM Service";
    @Override

    //_____________This Method gets a notification message from Firebase_____________
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }
}

