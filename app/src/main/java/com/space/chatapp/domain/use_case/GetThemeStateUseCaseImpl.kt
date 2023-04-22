package com.space.chatapp.domain.use_case

import com.space.chatapp.domain.local.repository.DataStoreRepository

class GetThemeStateUseCaseImpl(private val dataStoreRepository: DataStoreRepository) :
    GetThemeStateUseCase {

    override suspend fun invoke(): Result<String> {
        return dataStoreRepository.getThemeStateValue()
    }

}