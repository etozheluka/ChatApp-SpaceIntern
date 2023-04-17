package com.example.chatapp_spaceintern.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.chatapp_spaceintern.data.local.AppDataBase
import com.example.chatapp_spaceintern.data.local.repository.ChatMessageRepositoryImpl
import com.example.chatapp_spaceintern.data.local.repository.DataStoreRepositoryImpl
import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import com.example.chatapp_spaceintern.domain.use_case.DayNightPreferencesUseCase
import com.example.chatapp_spaceintern.domain.use_case.SendMessageUseCase
import com.example.chatapp_spaceintern.domain.use_case.ShowMessageUseCase
import com.example.chatapp_spaceintern.presentation.MainActivityViewModel
import com.example.chatapp_spaceintern.presentation.ui.SharedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

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

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = {androidApplication().preferencesDataStoreFile("darkMode_preference")}
        )
    }

    single<ChatMessageRepository> { ChatMessageRepositoryImpl(get()) }

    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }

    single {
        SendMessageUseCase(get())
    }

    single {
        ShowMessageUseCase(get())
    }
    single {
        DayNightPreferencesUseCase(get())
    }

    viewModel {
        SharedViewModel(get(),get())
    }

    viewModel {
        MainActivityViewModel(get())
    }
}


