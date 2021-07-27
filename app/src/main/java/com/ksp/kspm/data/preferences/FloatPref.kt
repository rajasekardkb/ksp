package com.ksp.kspm.data.preferences

import android.content.SharedPreferences

class FloatPref(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private var defaultValue: Float
) {

    fun getValue(): Float = sharedPreferences.getFloat(key, defaultValue)
    fun setValue(value: Float) = sharedPreferences.edit().putFloat(key, value).apply()
    fun remove() = sharedPreferences.edit().remove(key).apply()

}