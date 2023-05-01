package com.space.chatapp.domain.model

import com.space.chatapp.presentation.model.ChatUser

data class MessageModel(
    val id: Int? = null,
    val sender: ChatUser?,
    val message: String?,
    val time: Long?
)
