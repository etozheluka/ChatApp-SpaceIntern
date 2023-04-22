package com.space.chatapp.domain.use_case

import com.space.chatapp.domain.model.MessageModel

interface SendMessageUseCase {
    suspend fun invoke(chat: MessageModel)
}