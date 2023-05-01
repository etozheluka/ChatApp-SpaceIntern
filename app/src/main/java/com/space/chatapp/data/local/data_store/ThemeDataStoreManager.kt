package com.space.chatapp.data.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.space.chatapp.utils.ChatThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeDataStoreManager(private val dataStore: DataStore<Preferences>) {

    suspend fun saveValue(key: String, dayMode: ChatThemeMode) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = dayMode.name
        }
    }

    fun getValue(key: String, defaultValue: String = ""): Flow<String> {
        val preferences = dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: defaultValue
        }
        return preferences
    }
}