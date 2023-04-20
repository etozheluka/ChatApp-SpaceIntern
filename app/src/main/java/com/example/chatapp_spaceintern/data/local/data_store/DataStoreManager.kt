package com.example.chatapp_spaceintern.data.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    suspend fun edit(block: suspend (MutablePreferences) -> Unit) {
        dataStore.edit { preferences ->
            block(preferences)
        }
    }

    fun getFlow(key: Preferences.Key<String>, defaultValue: String = ""): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }
}