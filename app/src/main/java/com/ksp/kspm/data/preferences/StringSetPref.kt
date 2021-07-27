package com.ksp.kspm.data.preferences

import android.content.SharedPreferences

class StringSetPref(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private var defaultValue: Set<String>
) {

    fun getValue(): Set<String> = sharedPreferences.getStringSet(key, defaultValue)!!
    fun setValue(value: Set<String>) = sharedPreferences.edit().putStringSet(key, value).apply()
    fun remove() = sharedPreferences.edit().remove(key).apply()

}