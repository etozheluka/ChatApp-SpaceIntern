package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ShowMessageUseCase {
    fun invoke(): Flow<List<MessageModel>>
}