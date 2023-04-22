package com.space.chatapp.di

import com.space.chatapp.presentation.MainActivityViewModel
import com.space.chatapp.presentation.chat_screen.viewmodel.ChatFragmentViewModel
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