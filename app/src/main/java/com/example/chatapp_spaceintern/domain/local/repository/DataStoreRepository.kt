package com.example.chatapp_spaceintern.domain.local.repository

import com.example.chatapp_spaceintern.utils.ThemeModeEnum

interface DataStoreRepository {
    suspend fun putString(
        dayMode: ThemeModeEnum
    )

    suspend fun getString(): Result<String>
}