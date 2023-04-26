package com.space.chatapp.presentation.chat_activity.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.use_case.theme.get.GetThemeStateUseCase
import com.space.chatapp.domain.use_case.theme.save.SaveThemeStateUseCase
import com.space.chatapp.utils.ChatThemeMode
import com.space.chatapp.utils.extension.viewModelScope
import com.space.chatapp.utils.toAppCompatMode
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ChatHolderViewModel(
    private val saveThemeStateUseCase: SaveThemeStateUseCase,
    private val getThemeStateUseCase: GetThemeStateUseCase
) :
    ViewModel() {

    private val _state = MutableSharedFlow<ChatThemeMode>()
    val state = _state.asSharedFlow()

    private suspend fun getThemeStateValue(): Result<String> = getThemeStateUseCase.invoke()

    private fun saveThemeStateValue(themeMode: ChatThemeMode) {
        viewModelScope {
            saveThemeStateUseCase.invoke(themeMode)
            AppCompatDelegate.setDefaultNightMode(themeMode.toAppCompatMode())
        }
    }

    fun themeUpdate() {
        viewModelScope {
            when (getThemeStateValue().getOrNull()) {
                ChatThemeMode.DAY_MODE.name -> {
                    _state.emit(ChatThemeMode.NIGHT_MODE)
                    saveThemeStateValue(ChatThemeMode.NIGHT_MODE)
                }
                else -> {
                    _state.emit(ChatThemeMode.DAY_MODE)
                    saveThemeStateValue(ChatThemeMode.DAY_MODE)
                }
            }
        }
    }

    fun checkPreferencesStatus() {
        viewModelScope {
            when (getThemeStateValue().getOrNull()) {
                ChatThemeMode.DAY_MODE.name -> {
                    _state.emit(ChatThemeMode.DAY_MODE)
                    AppCompatDelegate.setDefaultNightMode(ChatThemeMode.DAY_MODE.toAppCompatMode())
                }
                else -> {
                    _state.emit(ChatThemeMode.NIGHT_MODE)
                    AppCompatDelegate.setDefaultNightMode(ChatThemeMode.NIGHT_MODE.toAppCompatMode())
                }
            }
        }
    }
}