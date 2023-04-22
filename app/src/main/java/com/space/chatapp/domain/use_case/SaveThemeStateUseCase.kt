package com.space.chatapp.domain.use_case

import com.space.chatapp.utils.ThemeModeEnum

interface SaveThemeStateUseCase {
    suspend fun invoke(dayMode: ThemeModeEnum)
}