package com.example.chatapp_spaceintern.data.local.repository

import com.example.chatapp_spaceintern.data.local.AppDataBase
import com.example.chatapp_spaceintern.data.mapper.toEntity
import com.example.chatapp_spaceintern.data.mapper.toModel
import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.model.MessageModel
import kotlinx.coroutines.flow.map

class ChatMessageRepositoryImpl (private val db: AppDataBase):ChatMessageRepository {

    override fun getAll() = db.chatDao().getAll().map { chat ->
        chat.map{
            it.toModel()
        }
    }

    override suspend fun insertAll(message: MessageModel) {
        db.chatDao().insertAll(message.toEntity())
    }
}