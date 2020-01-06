package com.example.fletesya

import android.app.Application

import com.example.fletesya.data.Storage.LocalStorage
import dagger.Component

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