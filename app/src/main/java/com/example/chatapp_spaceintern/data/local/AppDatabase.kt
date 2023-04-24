package com.example.chatapp_spaceintern.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chatapp_spaceintern.data.local.dao.ChatDao
import com.example.chatapp_spaceintern.data.local.entity.ChatEntity

@Database(
    entities = [ChatEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
}