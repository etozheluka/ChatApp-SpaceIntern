package com.example.chatapp_spaceintern.di

import androidx.room.Room
import com.example.chatapp_spaceintern.data.local.AppDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val roomModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java, "user_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single {
        val db = get<AppDataBase>()
        db.chatDao()
    }
}