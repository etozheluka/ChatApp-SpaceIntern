package com.example.chatapp_spaceintern.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp_spaceintern.domain.use_case.DayNightPreferencesUseCase
import com.example.chatapp_spaceintern.utils.StateHolder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dayNightPreferencesUseCase: DayNightPreferencesUseCase) :
    ViewModel() {

    private val _state = MutableSharedFlow<StateHolder>()
    val state = _state.asSharedFlow()

    private suspend fun getBoolean(): Result<Boolean> = dayNightPreferencesUseCase.getMode()

    private fun saveBoolean(dayMode: Boolean) {
        viewModelScope.launch {
            dayNightPreferencesUseCase.setMode(dayMode)
        }
    }


    fun dayNightHandling() {
        viewModelScope.launch {
            val mode = getBoolean()
            if (mode.getOrNull() == true) {
                _state.emit(StateHolder(false))
                saveBoolean(false)
            } else {
                _state.emit(StateHolder(true))
                saveBoolean(true)
            }
        }
    }

    fun checkPreferencesStatus() {
        viewModelScope.launch {
            val mode = getBoolean()
            if (mode.getOrNull() == true) {
                _state.emit(StateHolder(true))
            } else {
                _state.emit(StateHolder(false))
            }
        }
    }
}