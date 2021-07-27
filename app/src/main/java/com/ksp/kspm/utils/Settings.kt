package com.ksp.kspm.utils

import android.content.Context
import android.content.pm.PackageManager

class Settings(context : Context) {
    private val appContext = context.applicationContext
    private val publishableKeyMetadata = getMetadata(METADATA_KEY_PUBLISHABLE_KEY)

    val publishKey : String get() {
        return publishableKeyMetadata ?: PUBLISHABLE_KEY
    }

    private fun getMetadata(key: String): String? {
        return appContext.packageManager
            .getApplicationInfo(appContext.packageName, PackageManager.GET_META_DATA)
            .metaData
            .getString(key)
            .takeIf { it?.isNotBlank() == true }
    }

    companion object{
        private const val PUBLISHABLE_KEY = "pk_test_51HpXxADQUAE3J76hyYnPLBmuEoOFxCRhJgQM7KwUxMMbnulPOsEG1TjOAU9uuBQoJ13rDrBP2LCd0Qkex507hKoT00tYlO0bBE"
        private const val METADATA_KEY_PUBLISHABLE_KEY = "com.codeglo.gowhere.metadata.publishable_key"
    }
}