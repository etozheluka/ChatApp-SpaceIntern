package com.space.chatapp.domain.use_case

import com.space.chatapp.utils.ChatThemeMode

interface SaveThemeStateUseCase {
    suspend fun invoke(dayMode: ChatThemeMode)
}