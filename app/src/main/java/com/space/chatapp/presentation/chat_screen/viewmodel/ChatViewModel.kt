package com.space.chatapp.presentation.chat_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.domain.use_case.SendMessageUseCase
import com.space.chatapp.domain.use_case.ShowMessageUseCase
import com.space.chatapp.utils.extension.getCurrentTime
import com.space.chatapp.utils.extension.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val showMessageUseCase: ShowMessageUseCase,
) : ViewModel() {

    private var _messages = MutableSharedFlow<MessageModel?>()
    val messages get() = _messages.asSharedFlow()

    private fun provideMessageModel(editTextInput: String, tag: String) = MessageModel(
        id = null,
        sender = tag,
        message = editTextInput,
        time = getCurrentTime()
    )

    fun showMessages(): Flow<List<MessageModel>> = showMessageUseCase.invoke()

    fun sendMessage(editTextInput: String, tag: String) {
        viewModelScope {
            sendMessageUseCase.invoke(provideMessageModel(editTextInput,tag))
        }
    }

    fun sendNoInternetMessage(inputText: String, tag: String) {
        viewModelScope {
            _messages.emit(
                provideMessageModel(
                    inputText, tag
                )
            )
        }
    }
}