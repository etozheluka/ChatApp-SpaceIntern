package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.model.MessageModel

class SendMessageUseCaseImpl(private val repository: ChatMessageRepository) : SendMessageUseCase {

    override suspend fun invoke(chat: MessageModel) = repository.insertMessage(chat)
}