package com.example.chatapp_spaceintern.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException


class DataStoreRepositoryImpl (private val dataStorePreferences: DataStore<Preferences>):DataStoreRepository {

    override suspend fun putBoolean(dayMode: Boolean) {
        Result.runCatching {
            dataStorePreferences.edit {preferences ->
                preferences[booleanPreferencesKey(KEY_BOOL)] = dayMode

            }
        }
    }

    override suspend fun getBoolean(): Result<Boolean> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch {
                if (it is IOException){
                    emit(emptyPreferences())
                }else{
                    throw it
                }
            }.map {
                it[booleanPreferencesKey(KEY_BOOL)]
            }
            val value = flow.firstOrNull() ?: true
            value
        }
    }

    companion object{

        private const val KEY_BOOL = "BOOLEAN"

    }
}