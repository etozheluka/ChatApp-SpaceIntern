package com.example.chatapp_spaceintern.domain.local.repository

import com.example.chatapp_spaceintern.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ChatMessageRepository {

    fun getAll(): Flow<List<MessageModel>>

    suspend fun insertAll(message: MessageModel)
}