package com.ksp.kspm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
 import com.facebook.stetho.Stetho
import com.ksp.kspm.di.DaggerAppComponent
import com.ksp.kspm.utils.InternetConnectivityHelper
import com.ksp.kspm.utils.interfaces.NetworkChangeListener
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication(), NetworkChangeListener {

    companion object {
        /**
         * To Check whether the user is connected to the internet or not
         */
        var isConnected = false
            private set

        const val NETWORK_STATE_RECEIVER = "com.codeglo.gowhereuser.NETWORK_STATE_RECEIVER"
        const val NETWORK_STATE = "network_state"
        var notificationChannel: NotificationChannel? = null
        var notification_channel_id = "notification_channel"
        var notification_channel_name = "Gowgere"
        var notificationId = 3  //random number
    }




    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
         Timber.plant(Timber.DebugTree())
        InternetConnectivityHelper.registerListener(this, this)
        Stetho.initializeWithDefaults(this)
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                notification_channel_id,
                notification_channel_name,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationChannel?.enableLights(true)
            notificationChannel?.lightColor = Color.BLUE
            notificationChannel?.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }

    }

    override fun onTerminate() {
        //For safety only; just in case
        InternetConnectivityHelper.unRegisterListener()
        super.onTerminate()
    }

    override fun onAvailable() {
        isConnected = true
        broadcastNetworkStatus()
    }

    override fun onLost() {
        isConnected = false
        broadcastNetworkStatus()
    }

    private fun broadcastNetworkStatus() {
        sendBroadcast(Intent(NETWORK_STATE_RECEIVER).apply { putExtra(NETWORK_STATE, isConnected) })
    }



}