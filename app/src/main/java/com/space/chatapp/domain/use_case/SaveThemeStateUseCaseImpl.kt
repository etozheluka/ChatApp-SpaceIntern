package com.space.chatapp.domain.use_case

import com.space.chatapp.domain.local.repository.DataStoreRepository
import com.space.chatapp.utils.ChatThemeMode

class SaveThemeStateUseCaseImpl(private val dataStoreRepository: DataStoreRepository) :
    SaveThemeStateUseCase {

    override suspend fun invoke(dayMode: ChatThemeMode) {
        return dataStoreRepository.putThemeStateValue(dayMode)
    }
}