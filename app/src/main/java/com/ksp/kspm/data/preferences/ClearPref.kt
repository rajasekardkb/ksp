package com.ksp.kspm.data.preferences

import android.content.SharedPreferences

class ClearPref(private val sharedPreferences: SharedPreferences) {
    fun clear() = sharedPreferences.edit().clear().apply()
}