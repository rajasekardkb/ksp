package com.ksp.kspm.data.preferences

import android.content.SharedPreferences

class LongPref(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private var defaultValue: Long
) {

    fun getValue(): Long = sharedPreferences.getLong(key, defaultValue)
    fun setValue(value: Long) = sharedPreferences.edit().putLong(key, value).apply()
    fun remove() = sharedPreferences.edit().remove(key).apply()

}