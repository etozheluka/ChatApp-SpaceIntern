package com.example.chatapp_spaceintern.domain.use_case


interface GetThemeStateUseCase {
    suspend fun invoke(): Result<String>
}