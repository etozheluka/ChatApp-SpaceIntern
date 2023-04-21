package com.example.chatapp_spaceintern.domain.use_case

import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository

class GetThemeStateUseCaseImpl(private val dataStoreRepository: DataStoreRepository) :
    GetThemeStateUseCase {

    override suspend fun invoke(): Result<String> {
        return dataStoreRepository.getThemeStateValue()
    }

}