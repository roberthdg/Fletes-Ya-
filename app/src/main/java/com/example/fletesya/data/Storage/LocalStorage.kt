package com.example.fletesya.data.Storage

import com.example.fletesya.data.Preferences.MyPreferences
import dagger.Component

@Component(modules = [MyPreferences::class])
interface LocalStorage {


}