package com.example.chatapp_spaceintern.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "message_store")
data class ChatEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int?,
    @ColumnInfo(name = "sender")
    val sender: String?,
    @ColumnInfo(name = "message")
    val message: String?,
    @ColumnInfo(name = "time")
    val time: String?,
    @ColumnInfo(name = "internet")
    val internet: Boolean?
):Parcelable