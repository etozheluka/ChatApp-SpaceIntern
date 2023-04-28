package com.space.chatapp.data.repository

import com.space.chatapp.data.local.dao.ChatDao
import com.space.chatapp.data.mapper.toEntity
import com.space.chatapp.data.mapper.toModel
import com.space.chatapp.domain.local.repository.ChatMessageRepository
import com.space.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.map

class ChatMessageRepositoryImpl(private val dao:ChatDao) : ChatMessageRepository {

    override fun getAllMessages() = dao.getAllMessages().map { chat ->
        chat.map {
            it.toModel()
        }
    }

    override suspend fun insertMessage(message: MessageModel) {
        dao.insertMessage(message.toEntity())
    }
}