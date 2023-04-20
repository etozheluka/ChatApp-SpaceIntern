package com.example.chatapp_spaceintern.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp_spaceintern.domain.use_case.DayNightPreferencesUseCase
import com.example.chatapp_spaceintern.presentation.model.StateHolder
import com.example.chatapp_spaceintern.utils.ThemeModeEnum
import com.example.chatapp_spaceintern.utils.extension.launchWithViewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dayNightPreferencesUseCase: DayNightPreferencesUseCase) :
    ViewModel() {

    private val _state = MutableSharedFlow<StateHolder>()
    val state = _state.asSharedFlow()

    private suspend fun getThemeStateValue(): Result<String> = dayNightPreferencesUseCase.getMode()

    private fun saveThemeStateValue(dayMode: ThemeModeEnum) {
        viewModelScope.launch {
            dayNightPreferencesUseCase.setMode(dayMode)
        }
    }


    fun dayNightHandling() {
        launchWithViewModelScope {
            val mode = getThemeStateValue()
            if (mode.getOrNull() == ThemeModeEnum.DAY_MODE.mode) {
                _state.emit(StateHolder(ThemeModeEnum.NIGHT_MODE.mode))
                saveThemeStateValue(ThemeModeEnum.NIGHT_MODE)
            } else {
                _state.emit(StateHolder(ThemeModeEnum.DAY_MODE.mode))
                saveThemeStateValue(ThemeModeEnum.DAY_MODE)
            }
        }
    }

    fun checkPreferencesStatus() {
        launchWithViewModelScope {
            val mode = getThemeStateValue()
            if (mode.getOrNull() == ThemeModeEnum.DAY_MODE.mode) {
                _state.emit(StateHolder(ThemeModeEnum.DAY_MODE.mode))
            } else {
                _state.emit(StateHolder(ThemeModeEnum.NIGHT_MODE.mode))
            }
        }
    }
}