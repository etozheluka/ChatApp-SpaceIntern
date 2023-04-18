package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

class ShowMessageUseCase(private val repository: ChatMessageRepository) {

    operator fun invoke(): Flow<List<MessageModel>> {
        return repository.getAll()
    }
}