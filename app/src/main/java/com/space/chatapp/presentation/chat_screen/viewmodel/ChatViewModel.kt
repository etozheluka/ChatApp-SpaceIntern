package com.space.chatapp.presentation.chat_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.domain.use_case.SendMessageUseCase
import com.space.chatapp.domain.use_case.ShowMessageUseCase
import com.space.chatapp.presentation.model.UserEnum
import com.space.chatapp.utils.extension.getTimeInMills
import com.space.chatapp.utils.extension.ifEmpty
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

    private fun provideMessageModel(editTextInput: String, tag: UserEnum) = MessageModel(
        id = null, sender = tag, message = editTextInput, time = getTimeInMills()
    )

    fun showMessages(): Flow<List<MessageModel>> = showMessageUseCase.invoke()

    fun sendMessage(editTextInput: String, tag: UserEnum) {
        if(!editTextInput.ifEmpty()){
            viewModelScope {
                sendMessageUseCase.invoke(provideMessageModel(editTextInput, tag))
            }
        }

    }

    fun sendNoInternetMessage(editTextInput: String, tag: UserEnum) {
        if(!editTextInput.ifEmpty()){
            viewModelScope {
                _messages.emit(
                    provideMessageModel(
                        editTextInput, tag
                    )
                )
            }
        }

    }
}