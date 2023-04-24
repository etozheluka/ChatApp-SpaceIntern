package com.space.chatapp.utils

import androidx.appcompat.app.AppCompatDelegate

enum class ThemeModeEnum {
    DAY_MODE, NIGHT_MODE
}

fun ThemeModeEnum.toAppCompatMode(): Int {
    return when (this) {
        ThemeModeEnum.DAY_MODE -> AppCompatDelegate.MODE_NIGHT_NO
        ThemeModeEnum.NIGHT_MODE -> AppCompatDelegate.MODE_NIGHT_YES
    }
}