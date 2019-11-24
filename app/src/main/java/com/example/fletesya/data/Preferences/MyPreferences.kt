package com.example.fletesya.data.Preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class MyPreferences(
    context: Context
){

    private val appContext = context.applicationContext

    private val preference : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveToken(type: String, token: String) {
        preference.edit().putString(
            type,
            token
        ).apply()
    }

    fun getToken(type: String) : String? {
        return preference.getString(type, null)
    }



}