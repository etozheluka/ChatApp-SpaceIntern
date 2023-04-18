package com.example.chatapp_spaceintern.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException


class DataStoreRepositoryImpl(private val dataStorePreferences: DataStore<Preferences>) :
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
            val flow = dataStorePreferences.data.catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map {
                it[stringPreferencesKey(STRING_KEY)]
            }
            val value = flow.firstOrNull() ?: ""
            value
        }
    }

    companion object {
        private const val STRING_KEY = "string_key"
    }
}