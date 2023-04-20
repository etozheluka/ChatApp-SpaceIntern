package com.example.chatapp_spaceintern.data.repository

import com.example.chatapp_spaceintern.data.local.data_store.DataStoreManager
import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import com.example.chatapp_spaceintern.utils.ThemeModeEnum
import kotlinx.coroutines.flow.firstOrNull

class DataStoreRepositoryImpl(private val dataStorePreferences: DataStoreManager) :
    DataStoreRepository {

    override suspend fun putThemeStateValue(dayMode: ThemeModeEnum) {
        Result.runCatching {
            dataStorePreferences.saveValue(STRING_KEY,dayMode)
        }
    }

    override suspend fun getThemeStateValue(): Result<String> {
        return Result.runCatching {
            return Result.runCatching {
                val flow = dataStorePreferences.getValue(STRING_KEY)
                flow.firstOrNull() ?: ""
            }
        }
    }

    companion object {
        private const val STRING_KEY = "string_key"
    }
}