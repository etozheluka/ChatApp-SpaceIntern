package com.example.chatapp_spaceintern.data.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.chatapp_spaceintern.data.local.data_store.DataStoreManager
import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import kotlinx.coroutines.flow.firstOrNull


class DataStoreRepositoryImpl(private val dataStorePreferences: DataStoreManager) :
    DataStoreRepository {

    override suspend fun putString(dayMode: String) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[stringPreferencesKey(STRING_KEY)] = dayMode

            }
        }
    }

    override suspend fun getString(): Result<String> {
        return Result.runCatching {
            return Result.runCatching {
                val flow = dataStorePreferences.getFlow(stringPreferencesKey(STRING_KEY))
                flow.firstOrNull() ?: ""
            }
        }
    }

    companion object {
        private const val STRING_KEY = "string_key"
    }
}