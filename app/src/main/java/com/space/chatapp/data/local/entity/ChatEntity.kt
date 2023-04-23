package com.space.chatapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "message_store")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "sender")
    val sender: String?,
    @ColumnInfo(name = "message")
    val message: String?,
    @ColumnInfo(name = "time")
    val time: Long?,
)