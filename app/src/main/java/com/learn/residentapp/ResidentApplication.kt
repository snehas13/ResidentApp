package com.learn.residentapp

import android.app.Application
import com.learn.residentapp.di.appModule
import org.koin.android.ext.android.startKoin

class ResidentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}