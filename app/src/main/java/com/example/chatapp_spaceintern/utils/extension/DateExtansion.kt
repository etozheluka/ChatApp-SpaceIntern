package com.example.chatapp_spaceintern.utils.extension

import java.text.SimpleDateFormat
import java.util.*

fun currentTime(): String {
    val calendar = Calendar.getInstance()
    val dayMonthFormat = SimpleDateFormat("dd/MM, HH:mm", Locale.getDefault())
    return dayMonthFormat.format(calendar.time)
}
