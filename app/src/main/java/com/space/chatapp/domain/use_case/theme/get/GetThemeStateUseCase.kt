package com.space.chatapp.domain.use_case.theme.get


interface GetThemeStateUseCase {
    suspend fun invoke(): Result<String>
}