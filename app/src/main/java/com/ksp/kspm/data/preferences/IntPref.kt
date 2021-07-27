package com.ksp.kspm.data.preferences

import android.content.SharedPreferences

class IntPref(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private var defaultValue: Int
) {

    fun getValue(): Int =  sharedPreferences.getInt(key, defaultValue)
    fun setValue(value: Int)  = sharedPreferences.edit().putInt(key,value).apply()
    fun remove() = sharedPreferences.edit().remove(key).apply()

}