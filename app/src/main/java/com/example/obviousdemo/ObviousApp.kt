package com.example.obviousdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ObviousApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}