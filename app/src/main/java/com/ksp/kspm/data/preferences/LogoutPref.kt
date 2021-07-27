package com.ksp.kspm.data.preferences

import android.content.SharedPreferences
import com.ksp.kspm.di.module.PreferenceModule
import timber.log.Timber

class LogoutPref(private val sharedPreferences: SharedPreferences) {

    fun doLogout() {

        val success = sharedPreferences.edit().run {
            remove(PreferenceModule.AUTH_TOKEN)
            remove(PreferenceModule.LOGGEDIN_STATUS)
            remove(PreferenceModule.ID)
            remove(PreferenceModule.USER_DETAILS)

            commit()
        }

        if (success) {
            Timber.d("Removed successfully *** ")

            Timber.d(
                "Logged in status ***  ${sharedPreferences.getBoolean(
                    PreferenceModule.LOGGEDIN_STATUS,
                    false
                )}"
            )
            Timber.d(
                "Access token *** ${sharedPreferences.getString(
                    PreferenceModule.AUTH_TOKEN,
                    ""
                )}"
            )
            Timber.d("Id *** ${sharedPreferences.getString(PreferenceModule.ID, "")}")
            Timber.d(
                "User Details *** ${sharedPreferences.getString(
                    PreferenceModule.USER_DETAILS,
                    ""
                )}"
            )

        } else {
            Timber.d("Failed to remove")
        }
    }
}