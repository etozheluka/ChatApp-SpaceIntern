package com.example.chatapp_spaceintern.di

import com.example.chatapp_spaceintern.domain.use_case.*
import org.koin.dsl.module

val useCaseModule = module {
    single { SendMessageUseCaseImpl(get()) }
    single { ShowMessageUseCaseImpl(get()) }
    single<SaveThemeStateUseCase> { SaveThemeStateUseCaseImpl(get()) }
    single<GetThemeStateUseCase> { GetThemeStateUseCaseImpl(get()) }
    single<SendMessageUseCase> { SendMessageUseCaseImpl(get()) }
    single<ShowMessageUseCase> { ShowMessageUseCaseImpl(get()) }
}
