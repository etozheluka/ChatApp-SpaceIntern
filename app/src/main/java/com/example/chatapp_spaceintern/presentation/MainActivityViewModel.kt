package com.example.chatapp_spaceintern.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp_spaceintern.domain.use_case.DayNightPreferencesUseCase
import com.example.chatapp_spaceintern.utils.StateHolder
import com.example.chatapp_spaceintern.utils.ThemeMode
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dayNightPreferencesUseCase: DayNightPreferencesUseCase) :
    ViewModel() {

    private val _state = MutableSharedFlow<StateHolder>()
    val state = _state.asSharedFlow()

    private suspend fun getString(): Result<String> = dayNightPreferencesUseCase.getMode()

    private fun saveString(dayMode: String) {
        viewModelScope.launch {
            dayNightPreferencesUseCase.setMode(dayMode)
        }
    }


    fun dayNightHandling() {
        viewModelScope.launch {
            val mode = getString()
            if (mode.getOrNull() == ThemeMode.DAY_MODE.mode) {
                _state.emit(StateHolder(ThemeMode.NIGHT_MODE.mode))
                saveString(ThemeMode.NIGHT_MODE.mode)
            } else {
                _state.emit(StateHolder(ThemeMode.DAY_MODE.mode))
                saveString(ThemeMode.DAY_MODE.mode)
            }
        }
    }

    fun checkPreferencesStatus() {
        viewModelScope.launch {
            val mode = getString()
            if (mode.getOrNull() == ThemeMode.DAY_MODE.mode) {
                _state.emit(StateHolder(ThemeMode.DAY_MODE.mode))
            } else {
                _state.emit(StateHolder(ThemeMode.NIGHT_MODE.mode))
            }
        }
    }
}