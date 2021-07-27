package com.ksp.kspm.utils.interfaces

/**
 * To listen for network connection availability
 */
interface NetworkChangeListener {
    /**
     * Called when the user has a valid internet connection
     */
    fun onAvailable()

    /**
     * Called when the user's internet connection is lost
     */
    fun onLost()
}