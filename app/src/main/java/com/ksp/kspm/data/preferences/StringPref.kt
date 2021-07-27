package com.ksp.kspm.data.preferences

import android.content.SharedPreferences

class StringPref(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private var defaultValue: String?
) {

    fun getValue(): String = sharedPreferences.getString(key, defaultValue)!!
    fun setValue(value: String) = sharedPreferences.edit().putString(key, value).commit()
    fun remove() = sharedPreferences.edit().remove(key).apply()

}