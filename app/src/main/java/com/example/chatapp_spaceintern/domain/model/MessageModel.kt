package com.example.chatapp_spaceintern.domain.model

import com.example.chatapp_spaceintern.presentation.base.BaseDiff

data class MessageModel(
    val id: Int?,
    val sender: String?,
    val message: String?,
    val time: String?
): BaseDiff<MessageModel>() {
    override val inner: MessageModel
        get() = this
    override val uniqueValue: Any
        get() = id ?: ""

    override fun compareTo(other: Any?): Boolean {
        return other is MessageModel && this == other
    }
}
