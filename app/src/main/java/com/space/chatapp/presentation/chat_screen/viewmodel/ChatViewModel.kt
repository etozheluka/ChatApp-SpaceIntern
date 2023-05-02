package com.space.chatapp.presentation.chat_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.domain.use_case.message.send.SendMessageUseCase
import com.space.chatapp.domain.use_case.message.show.ShowMessageUseCase
import com.space.chatapp.utils.extension.getTimeInMills
import com.space.chatapp.utils.extension.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val showMessageUseCase: ShowMessageUseCase,
) : ViewModel() {

    fun showMessages(): Flow<List<MessageModel>> = showMessageUseCase.invoke()

    fun sendMessage(editTextInput: String, tag: String, isOnline: Boolean) {
        if (editTextInput.isNotEmpty()) {
            viewModelScope {
                sendMessageUseCase.invoke(
                    MessageModel(
                        sender = tag,
                        message = editTextInput,
                        time = getTimeInMills(),
                        isOnline = isOnline
                    )
                )
            }
        }
    }
}