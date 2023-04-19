package com.example.chatapp_spaceintern.di

import android.content.Context
import androidx.room.Room
import com.example.chatapp_spaceintern.data.local.AppDataBase
import org.koin.dsl.module

private fun provideChatDataBase(context: Context): AppDataBase {
    return Room.databaseBuilder(context, AppDataBase::class.java, "chat_database").fallbackToDestructiveMigration().build()
}

private fun provideDao(db: AppDataBase) = db.chatDao()

val dataBaseModule = module {
    single { provideChatDataBase(get()) }
    single { provideDao(get()) }
}