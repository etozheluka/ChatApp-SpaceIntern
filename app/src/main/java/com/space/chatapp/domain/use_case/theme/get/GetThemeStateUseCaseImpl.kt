package com.space.chatapp.domain.use_case.theme.get

import com.space.chatapp.domain.local.repository.ThemeDataStoreRepository

class GetThemeStateUseCaseImpl(private val themeDataStoreRepository: ThemeDataStoreRepository) :
    GetThemeStateUseCase {

    override suspend fun invoke(): Result<String> {
        return themeDataStoreRepository.getThemeStateValue()
    }

}