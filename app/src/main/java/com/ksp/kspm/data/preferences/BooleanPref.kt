package com.ksp.kspm.data.preferences

import android.content.SharedPreferences

class BooleanPref(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private var defaultValue: Boolean
) {

    fun getValue(): Boolean =  sharedPreferences.getBoolean(key, defaultValue)
    fun setValue(value: Boolean)  = sharedPreferences.edit().putBoolean(key,value).apply()
    fun remove() = sharedPreferences.edit().remove(key).apply()

}