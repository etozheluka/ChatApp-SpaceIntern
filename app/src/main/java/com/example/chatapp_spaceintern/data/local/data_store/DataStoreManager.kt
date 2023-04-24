package com.example.chatapp_spaceintern.data.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.chatapp_spaceintern.utils.ThemeModeEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    suspend fun saveValue(key: String, dayMode: ThemeModeEnum) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = dayMode.mode
        }
    }

    fun getValue(key: String, defaultValue: String = ""): Flow<String> {
        val preferences = dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: defaultValue
        }
        return preferences
    }
}