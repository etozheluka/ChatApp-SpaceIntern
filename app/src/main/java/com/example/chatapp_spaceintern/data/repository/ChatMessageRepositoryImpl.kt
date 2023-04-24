package com.example.chatapp_spaceintern.data.repository

import com.example.chatapp_spaceintern.data.local.dao.ChatDao
import com.example.chatapp_spaceintern.data.mapper.toEntity
import com.example.chatapp_spaceintern.data.mapper.toModel
import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.model.MessageModel
import kotlinx.coroutines.flow.map

class ChatMessageRepositoryImpl(private val dao:ChatDao) : ChatMessageRepository {

    override fun getAll() = dao.getAll().map { chat ->
        chat.map {
            it.toModel()
        }
    }

    override suspend fun insertMessage(message: MessageModel) {
        dao.insertMessage(message.toEntity())
    }
}