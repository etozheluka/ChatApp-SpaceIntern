package com.example.chatapp_spaceintern.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp_spaceintern.domain.use_case.DayNightPreferencesUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel( private val dayNightPreferencesUseCase: DayNightPreferencesUseCase):ViewModel() {

    fun saveBoolean(dayMode:Boolean){
        viewModelScope.launch {
            dayNightPreferencesUseCase.setMode(dayMode)
        }
    }
    suspend fun getBoolean():Result<Boolean> = dayNightPreferencesUseCase.getMode()
}