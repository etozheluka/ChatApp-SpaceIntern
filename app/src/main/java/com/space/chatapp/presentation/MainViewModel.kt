package com.space.chatapp.presentation

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.use_case.GetThemeStateUseCase
import com.space.chatapp.domain.use_case.SaveThemeStateUseCase
import com.space.chatapp.utils.ThemeModeEnum
import com.space.chatapp.utils.extension.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainViewModel(
    private val saveThemeStateUseCase: SaveThemeStateUseCase,
    private val getThemeStateUseCase: GetThemeStateUseCase
) :
    ViewModel() {

    private val _state = MutableSharedFlow<ThemeModeEnum>()
    val state = _state.asSharedFlow()

    private suspend fun getThemeStateValue(): Result<String> = getThemeStateUseCase.invoke()

    private fun saveThemeStateValue(dayMode: ThemeModeEnum) {
        viewModelScope {
            saveThemeStateUseCase.invoke(dayMode)
        }
    }

    fun dayNightHandling() {
        viewModelScope {
            when (getThemeStateValue().getOrNull()) {
                ThemeModeEnum.DAY_MODE.name -> {
                    _state.emit(ThemeModeEnum.NIGHT_MODE)
                    saveThemeStateValue(ThemeModeEnum.NIGHT_MODE)
                }
                else -> {
                    _state.emit(ThemeModeEnum.DAY_MODE)
                    saveThemeStateValue(ThemeModeEnum.DAY_MODE)
                }
            }
        }
    }

    fun checkPreferencesStatus() {
        viewModelScope {
            when (getThemeStateValue().getOrNull()) {
                ThemeModeEnum.DAY_MODE.name -> _state.emit(ThemeModeEnum.DAY_MODE)
                else -> _state.emit(ThemeModeEnum.NIGHT_MODE)
            }
        }
    }
}