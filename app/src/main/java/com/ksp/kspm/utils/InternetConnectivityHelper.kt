package com.ksp.kspm.utils

import android.content.Context
import android.net.*
import android.os.Build
import com.ksp.kspm.utils.interfaces.NetworkChangeListener
import timber.log.Timber

object InternetConnectivityHelper : ConnectivityManager.NetworkCallback() {

    private var networkChangeListener: NetworkChangeListener? = null
    private lateinit var cManager: ConnectivityManager
    var isConnected: Boolean = false

    /**
     * Initally register a connectivity manager to listener for network changes through [ConnectivityManager.NetworkCallback]
     *
     * @param networkChangeListener is a internal listener to check connection availabilty
     * @param context
     */
    fun registerListener(networkChangeListener: NetworkChangeListener, context: Context) {
        Timber.d("Registering network callback")
        InternetConnectivityHelper.networkChangeListener = networkChangeListener
        cManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cManager.registerDefaultNetworkCallback(this)
        } else {
            val networkChangeFilter =
                NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()
            cManager.registerNetworkCallback(networkChangeFilter, this)
        }
    }

    /**
     * Un register , any previously registered call back
     *
     * @throws Exception when trying to unregister more than once
     */
    fun unRegisterListener() {
        try {
            cManager.unregisterNetworkCallback(this)
        } catch (e: Exception) {
            Timber.d("Unregistering network callback $e")
        }
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        networkChangeListener?.onAvailable()
        Timber.d("===== IS CONNECTED onAvailable")
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        networkChangeListener?.onLost()
        Timber.d("===== IS NOT CONNECTED onLost")
    }


}