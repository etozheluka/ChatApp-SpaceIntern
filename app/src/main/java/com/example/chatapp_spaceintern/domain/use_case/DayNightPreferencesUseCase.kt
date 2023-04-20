package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import com.example.chatapp_spaceintern.utils.ThemeModeEnum

class DayNightPreferencesUseCase(private val dataStoreRepository: DataStoreRepository) {

    suspend fun setMode(dayMode: ThemeModeEnum) {
        return dataStoreRepository.putString(dayMode)
    }

    suspend fun getMode(): Result<String> {
        return dataStoreRepository.getString()
    }
}