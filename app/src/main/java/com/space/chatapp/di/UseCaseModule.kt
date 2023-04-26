package com.space.chatapp.di

import com.space.chatapp.domain.use_case.message.send.SendMessageUseCase
import com.space.chatapp.domain.use_case.message.send.SendMessageUseCaseImpl
import com.space.chatapp.domain.use_case.message.show.ShowMessageUseCase
import com.space.chatapp.domain.use_case.message.show.ShowMessageUseCaseImpl
import com.space.chatapp.domain.use_case.theme.get.GetThemeStateUseCase
import com.space.chatapp.domain.use_case.theme.get.GetThemeStateUseCaseImpl
import com.space.chatapp.domain.use_case.theme.save.SaveThemeStateUseCase
import com.space.chatapp.domain.use_case.theme.save.SaveThemeStateUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single { SendMessageUseCaseImpl(get()) }
    single { ShowMessageUseCaseImpl(get()) }
    single<SaveThemeStateUseCase> { SaveThemeStateUseCaseImpl(get()) }
    single<GetThemeStateUseCase> { GetThemeStateUseCaseImpl(get()) }
    single<SendMessageUseCase> { SendMessageUseCaseImpl(get()) }
    single<ShowMessageUseCase> { ShowMessageUseCaseImpl(get()) }
}
