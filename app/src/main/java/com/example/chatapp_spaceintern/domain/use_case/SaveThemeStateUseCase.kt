package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.utils.ThemeModeEnum

interface SaveThemeStateUseCase {
    suspend fun invoke(dayMode: ThemeModeEnum)
}