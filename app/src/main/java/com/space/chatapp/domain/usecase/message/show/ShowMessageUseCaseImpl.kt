package com.space.chatapp.domain.usecase.message.show

import com.space.chatapp.domain.local.repository.ChatMessageRepository
import com.space.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

class ShowMessageUseCaseImpl(private val repository: ChatMessageRepository) : ShowMessageUseCase {

    override operator fun invoke(): Flow<List<MessageModel>> {
        return repository.getAllMessages()
    }
}