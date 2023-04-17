package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.model.MessageModel

class SendMessageUseCase (private val repository: ChatMessageRepository) {

    suspend fun invoke(chat:MessageModel) = repository.insertAll(chat)
}