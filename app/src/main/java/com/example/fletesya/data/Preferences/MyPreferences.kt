package com.example.fletesya.data.Preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class MyPreferences(
    context: Context
){

    private val appContext = context.applicationContext

    private val preference : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    @Provides
    fun saveToken(type: String, token: String) {
        preference.edit().putString(
            type,
            token
        ).apply()
    }

    @Provides
    fun saveUserData(type: String, data: String) {
        preference.edit().putString(
            type,
            data
        ).apply()
    }

    @Provides
    fun getData(type: String) : String? {
        return preference.getString(type, null)
    }

    @Provides
    fun clear() {
        val editor = preference.edit()
        editor.clear()
        editor.commit()
    }

}