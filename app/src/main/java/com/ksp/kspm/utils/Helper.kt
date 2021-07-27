package com.ksp.kspm.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.core.content.ContextCompat
import timber.log.Timber
import java.util.*

object Helper {
    val spaceRegex = " ".toRegex()

    fun getAddressFromLocation(location: Location, context: Context): String {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            address.forEach {
                return if (it.adminArea == null) {
                    it.locality
                } else {
                    Timber.d("------------- $geocoder ---")
                    Timber.d("----------- $it")

                    if (it.locality == null) {
                        formatAddressWith(it.subLocality, it.adminArea)
                    } else {
                        formatAddressWith(it.locality, it.adminArea)
                    }

                }
            }
            return address[0].locality
        } catch (e: Exception) {
            Timber.d("${e.message} : Address not found")
            return "N/A"
        }
    }

    private fun formatAddressWith(locality: String, adminArea: String): String {
        return if (adminArea.split(spaceRegex).size >= 2) {
            "$locality, ${firstWordFirstLetter(adminArea)}${lastWordFirstLetter(adminArea)}"
        } else {
            "$locality, ${adminArea.substring(0, 2).toUpperCase(Locale.ROOT)}"
        }
    }

    private fun lastWordFirstLetter(it: String) =
        it.split(spaceRegex).last().first().toUpperCase()

    private fun firstWordFirstLetter(it: String) =
        it.split(spaceRegex).first().first().toUpperCase()


    fun Context.isLocationPermissionAvailable(): Boolean =
        (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED)

}
