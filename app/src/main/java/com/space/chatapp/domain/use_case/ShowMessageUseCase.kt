package com.space.chatapp.domain.use_case

import com.space.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ShowMessageUseCase {
    fun invoke(): Flow<List<MessageModel>>
}