package com.ksp.kspm.utils
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.ksp.kspm.R

object ConnectivityUtil {

    @Suppress("DEPRECATION")
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun isConnectedWithToast(context: Context):Boolean{
        if(!isConnected(context)){
          //  context.showToast(context.resources.getString(R.string.internetConnection))
            return false
        }
        return true

    }
}