package com.space.chatapp.domain.local.repository

import com.space.chatapp.utils.ThemeModeEnum

interface DataStoreRepository {
    suspend fun putThemeStateValue(
        dayMode: ThemeModeEnum
    )

    suspend fun getThemeStateValue(): Result<String>
}