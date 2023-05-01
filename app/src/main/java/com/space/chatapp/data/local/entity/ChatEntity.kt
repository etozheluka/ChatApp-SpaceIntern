package com.space.chatapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.space.chatapp.presentation.model.ChatUser


@Entity(tableName = "message_store")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val sender: ChatUser?,
    val message: String?,
    val time: Long?,
)