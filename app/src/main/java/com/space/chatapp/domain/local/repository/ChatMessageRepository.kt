package com.space.chatapp.domain.local.repository

import com.space.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ChatMessageRepository {

    fun getAll(): Flow<List<MessageModel>>

    suspend fun insertMessage(message: MessageModel)
}