package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository

class DayNightPreferencesUseCase(private val dataStoreRepository: DataStoreRepository) {

    suspend fun setMode(dayMode: String) {
        return dataStoreRepository.putString(dayMode)
    }

    suspend fun getMode(): Result<String> {
        return dataStoreRepository.getString()
    }
}