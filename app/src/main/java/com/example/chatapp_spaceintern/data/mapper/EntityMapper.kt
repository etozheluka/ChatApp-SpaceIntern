package com.example.chatapp_spaceintern.data.mapper

import com.example.chatapp_spaceintern.data.local.entity.ChatEntity
import com.example.chatapp_spaceintern.domain.model.MessageModel

fun MessageModel.toEntity() = ChatEntity(
    id = id,
    sender = sender,
    message = message,
    time = time,
    internet = internet
)
fun ChatEntity.toModel() = MessageModel(
    id = id,
    sender = sender,
    message = message,
    time = time,
    internet = internet
)