package com.example.chatapp_spaceintern.di

import com.example.chatapp_spaceintern.data.repository.ChatMessageRepositoryImpl
import com.example.chatapp_spaceintern.data.repository.DataStoreRepositoryImpl
import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ChatMessageRepository> { ChatMessageRepositoryImpl(get()) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
}