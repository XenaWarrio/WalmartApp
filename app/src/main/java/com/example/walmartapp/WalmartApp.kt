package com.example.walmartapp

import android.app.Application
import com.example.walmartapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WalmartApp : Application() {
        override fun onCreate() {
            super.onCreate()

            startKoin{
                androidLogger()
                androidContext(this@WalmartApp)
                modules(appModule)
            }
        }
}