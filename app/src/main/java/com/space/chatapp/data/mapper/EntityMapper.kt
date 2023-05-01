package com.space.chatapp.data.mapper

import com.space.chatapp.data.local.entity.ChatEntity
import com.space.chatapp.domain.model.MessageModel

fun MessageModel.toEntity() = ChatEntity(
    id = id,
    sender = sender,
    message = message,
    time = time,
)
fun ChatEntity.toModel() = MessageModel(
    id = id,
    sender = sender,
    message = message,
    time = time,
)