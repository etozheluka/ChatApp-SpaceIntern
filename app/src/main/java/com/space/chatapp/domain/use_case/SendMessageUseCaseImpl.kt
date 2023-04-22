package com.space.chatapp.domain.use_case

import com.space.chatapp.domain.local.repository.ChatMessageRepository
import com.space.chatapp.domain.model.MessageModel

class SendMessageUseCaseImpl(private val repository: ChatMessageRepository) : SendMessageUseCase {

    override suspend fun invoke(chat: MessageModel) = repository.insertMessage(chat)
}