package com.space.chatapp.domain.local.repository

import com.space.chatapp.utils.ChatThemeMode

interface ThemeDataStoreRepository {
    suspend fun putThemeStateValue(
        mode: ChatThemeMode
    )

    suspend fun getThemeStateValue(): Result<String>
}