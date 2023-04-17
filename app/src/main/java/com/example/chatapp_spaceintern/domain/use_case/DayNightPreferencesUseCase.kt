package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository

class DayNightPreferencesUseCase (private val dataStoreRepository:DataStoreRepository){

    suspend fun setMode(dayMode:Boolean){
        return dataStoreRepository.putBoolean(dayMode)
    }

    suspend fun getMode():Result<Boolean>{
        return dataStoreRepository.getBoolean()
    }
}