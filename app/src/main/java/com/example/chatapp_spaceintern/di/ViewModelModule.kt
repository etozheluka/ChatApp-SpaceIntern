package com.example.chatapp_spaceintern.di

import com.example.chatapp_spaceintern.presentation.MainActivityViewModel
import com.example.chatapp_spaceintern.presentation.chat_screen.viewmodel.ChatFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ChatFragmentViewModel(get(), get())
    }

    viewModel {
        MainActivityViewModel(get(),get())
    }
}