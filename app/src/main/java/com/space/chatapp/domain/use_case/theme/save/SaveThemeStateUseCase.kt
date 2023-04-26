package com.space.chatapp.domain.use_case.theme.save

import com.space.chatapp.utils.ChatThemeMode

interface SaveThemeStateUseCase {
    suspend fun invoke(themeMode: ChatThemeMode)
}