package com.space.chatapp.di

import com.space.chatapp.presentation.MainViewModel
import com.space.chatapp.presentation.chat_screen.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ChatViewModel(get(), get())
    }

    viewModel {
        MainViewModel(get(),get())
    }
}