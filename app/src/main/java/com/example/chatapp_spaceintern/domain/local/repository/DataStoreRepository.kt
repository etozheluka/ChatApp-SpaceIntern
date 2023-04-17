package com.example.chatapp_spaceintern.domain.local.repository

interface DataStoreRepository {
    suspend fun putBoolean(
        dayMode:Boolean
    )

    suspend fun getBoolean():Result<Boolean>
}