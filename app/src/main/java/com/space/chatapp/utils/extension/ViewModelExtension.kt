package com.space.chatapp.utils.extension

import androidx.lifecycle.ViewModel

fun ViewModel.getTimeInMills(): Long{
    return System.currentTimeMillis()
}