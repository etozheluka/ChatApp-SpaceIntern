package com.example.chatapp_spaceintern.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp_spaceintern.domain.use_case.DayNightPreferencesUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel( private val dayNightPreferencesUseCase: DayNightPreferencesUseCase):ViewModel() {

    private fun saveBoolean(dayMode:Boolean){
        viewModelScope.launch {
            dayNightPreferencesUseCase.setMode(dayMode)
        }
    }
    private suspend fun getBoolean():Result<Boolean> = dayNightPreferencesUseCase.getMode()

    fun dayNightHandling(dayNightMode:() ->Unit, nightDayMode:() ->Unit){
        viewModelScope.launch {
            val mode = getBoolean()
            if (mode.getOrNull() == true) {
                dayNightMode()
                saveBoolean(false)
            } else {
                nightDayMode()
                saveBoolean(true)
            }
        }
    }

     fun checkPreferencesStatus(nightDayMode:() ->Unit, dayNightMode:() ->Unit) {
         viewModelScope.launch {
            val mode = getBoolean()
            if (mode.getOrNull() == true) {
                nightDayMode()
            } else {
                dayNightMode()
            }
        }
    }
}