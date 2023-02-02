package com.example.petadoptmobile

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessageingService : FirebaseMessagingService(){
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        if(p0.notification!=null){
            showNotif(p0.notification!!.title,
                p0.notification!!.body)
        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.w("TOKEN FIREBASE",p0)
    }

    private fun showNotif(title: String?, body: String?) {
        var myNotivyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val myNotificationChannel = NotificationChannel(CHANNEL_1_ID,
            "Notification",
            NotificationManager.IMPORTANCE_DEFAULT).apply {
            description="FCM Channel 1"
            enableLights(true)
            lightColor = Color.WHITE
        }
        myNotivyManager.createNotificationChannel(myNotificationChannel)

        var Notif = NotificationCompat.Builder(this, CHANNEL_1_ID).apply {
            setDefaults(Notification.DEFAULT_ALL)
            setWhen(System.currentTimeMillis())
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle(title)
            setContentText(body)
            setContentInfo("Info")
        }
        myNotivyManager.notify(NOTIVY_1_ID,Notif.build())
    }
    companion object{
        val CHANNEL_1_ID = "channel1"
        val NOTIVY_1_ID = 1011
    }
}