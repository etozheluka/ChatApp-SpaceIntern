package com.space.chatapp.domain.use_case


interface GetThemeStateUseCase {
    suspend fun invoke(): Result<String>
}