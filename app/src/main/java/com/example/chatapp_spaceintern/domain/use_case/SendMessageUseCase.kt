package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.model.MessageModel

interface SendMessageUseCase {
    suspend fun invoke(chat: MessageModel)
}