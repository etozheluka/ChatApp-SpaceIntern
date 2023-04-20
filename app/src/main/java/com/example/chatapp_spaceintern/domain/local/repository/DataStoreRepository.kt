package com.example.chatapp_spaceintern.domain.local.repository

import com.example.chatapp_spaceintern.utils.ThemeModeEnum

interface DataStoreRepository {
    suspend fun putThemeStateValue(
        dayMode: ThemeModeEnum
    )

    suspend fun getThemeStateValue(): Result<String>
}