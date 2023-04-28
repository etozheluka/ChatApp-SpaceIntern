package com.space.chatapp.di

import com.space.chatapp.presentation.chat_screen.ui.chat_style.ChatStyleConfigurator
import com.space.chatapp.presentation.chat_screen.ui.chat_style.ChatStyleConfiguratorImpl
import com.space.chatapp.presentation.model.ChatUser
import org.koin.dsl.module

val appModule = module {
    factory<ChatStyleConfigurator> { (sender: ChatUser) -> ChatStyleConfiguratorImpl(sender) }
}