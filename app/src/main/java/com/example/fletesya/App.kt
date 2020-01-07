package com.example.fletesya

import android.app.Application

class App : Application() {

    companion object {

        private lateinit var instance : App

        fun getContext() = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }


}