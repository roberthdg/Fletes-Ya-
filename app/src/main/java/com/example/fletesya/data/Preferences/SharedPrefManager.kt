package com.example.fletesya.data.Preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


private val  REFRESH_KEY = "hhhhhh"

class SharedPrefManager(
    context: Context ){

    private val appContext = context.applicationContext

    private val preference : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveToken(refreshToken: String) {

        preference.edit().putString(
            REFRESH_KEY,
            refreshToken
        ).apply()

    }

    fun getToken() : String? {
        return preference.getString(REFRESH_KEY, null)
    }



}