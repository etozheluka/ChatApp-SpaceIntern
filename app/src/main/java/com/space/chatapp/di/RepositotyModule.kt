package com.space.chatapp.di

import com.space.chatapp.data.repository.ChatMessageRepositoryImpl
import com.space.chatapp.data.repository.DataStoreRepositoryImpl
import com.space.chatapp.domain.local.repository.ChatMessageRepository
import com.space.chatapp.domain.local.repository.DataStoreRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ChatMessageRepository> { ChatMessageRepositoryImpl(get()) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
}