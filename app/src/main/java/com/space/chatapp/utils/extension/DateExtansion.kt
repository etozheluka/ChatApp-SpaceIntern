package com.space.chatapp.utils.extension

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val dayMonthFormat = SimpleDateFormat("dd/MM, HH:mm", Locale.getDefault())
    return dayMonthFormat.format(calendar.time)
}
