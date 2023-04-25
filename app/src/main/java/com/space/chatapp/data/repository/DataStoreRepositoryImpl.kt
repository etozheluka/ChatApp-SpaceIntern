package com.space.chatapp.data.repository

import com.space.chatapp.data.local.data_store.DataStoreManager
import com.space.chatapp.domain.local.repository.DataStoreRepository
import com.space.chatapp.utils.ChatThemeMode
import kotlinx.coroutines.flow.firstOrNull

class DataStoreRepositoryImpl(private val dataStorePreferences: DataStoreManager) :
    DataStoreRepository {

    override suspend fun putThemeStateValue(dayMode: ChatThemeMode) {
        Result.runCatching {
            dataStorePreferences.saveValue(STRING_KEY,dayMode)
        }
    }

    override suspend fun getThemeStateValue(): Result<String> {
        return Result.runCatching {
            val flow = dataStorePreferences.getValue(STRING_KEY)
            flow.firstOrNull() ?: ""
        }
    }

    companion object {
        private const val STRING_KEY = "string_key"
    }
}