package com.example.chatapp_spaceintern.domain.local.repository

interface DataStoreRepository {
    suspend fun putString(
        dayMode: String
    )

    suspend fun getString(): Result<String>
}