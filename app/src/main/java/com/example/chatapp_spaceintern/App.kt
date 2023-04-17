package com.example.chatapp_spaceintern

import android.app.Application
import com.example.chatapp_spaceintern.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}