package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import com.example.chatapp_spaceintern.utils.ThemeModeEnum

class SaveThemeStateUseCaseImpl(private val dataStoreRepository: DataStoreRepository) :
    SaveThemeStateUseCase {

    override suspend fun invoke(dayMode: ThemeModeEnum) {
        return dataStoreRepository.putThemeStateValue(dayMode)
    }
}