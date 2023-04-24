package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

class ShowMessageUseCaseImpl(private val repository: ChatMessageRepository) : ShowMessageUseCase {

    override operator fun invoke(): Flow<List<MessageModel>> {
        return repository.getAll()
    }
}