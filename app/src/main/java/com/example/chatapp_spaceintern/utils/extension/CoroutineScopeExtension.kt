package com.example.chatapp_spaceintern.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.launchWithLifecycle(
    block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch { block() }
}

fun ViewModel.launchWithViewModelScope(
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch { block() }
}