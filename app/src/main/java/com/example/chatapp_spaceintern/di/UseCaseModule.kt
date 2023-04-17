package com.example.chatapp_spaceintern.di

import com.example.chatapp_spaceintern.domain.use_case.DayNightPreferencesUseCase
import com.example.chatapp_spaceintern.domain.use_case.SendMessageUseCase
import com.example.chatapp_spaceintern.domain.use_case.ShowMessageUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        SendMessageUseCase(get())
    }

    single {
        ShowMessageUseCase(get())
    }
    single {
        DayNightPreferencesUseCase(get())
    }
}
