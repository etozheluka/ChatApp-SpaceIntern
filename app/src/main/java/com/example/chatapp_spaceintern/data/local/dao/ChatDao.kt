package com.example.chatapp_spaceintern.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp_spaceintern.data.local.entity.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM message_store")
    fun getAll(): Flow<List<ChatEntity>>

    @Insert
    suspend fun insertAll(vararg users: ChatEntity)
}