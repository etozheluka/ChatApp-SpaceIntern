package com.space.chatapp.utils.mapper

import com.space.chatapp.data.local.entity.ChatEntity
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.utils.extension.convertTimeToLong
import com.space.chatapp.utils.extension.convertTimeToPattern
import java.text.SimpleDateFormat
import java.util.*

fun MessageModel.toEntity() = ChatEntity(
    id = id,
    sender = sender,
    message = message,
    time = time,
    isOnline = isOnline
)
fun ChatEntity.toDomainModel() = MessageModel(
    id = id,
    sender = sender,
    message = message,
    time = time,
    isOnline = isOnline
)
fun MessageModel.toPresentationModel() = Message(
    id = id,
    sender = sender,
    message = message,
    time = time?.convertTimeToPattern(),
    isOnline = isOnline
)


fun Message.toDomainModel() = MessageModel(
    id = id,
    sender = sender,
    message = message,
    time = time?.let { convertTimeToLong(it) },
    isOnline = isOnline
)