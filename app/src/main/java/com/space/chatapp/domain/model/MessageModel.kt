package com.space.chatapp.domain.model

import com.space.chatapp.presentation.model.UserEnum

data class MessageModel(
    val id: Int?,
    val sender: UserEnum?,
    val message: String?,
    val time: Long?
)
