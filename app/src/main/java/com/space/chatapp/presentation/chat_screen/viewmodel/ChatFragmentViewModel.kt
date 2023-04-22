package com.space.chatapp.presentation.chat_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.domain.use_case.SendMessageUseCase
import com.space.chatapp.domain.use_case.ShowMessageUseCase
import com.space.chatapp.utils.extension.viewModelScope
import kotlinx.coroutines.flow.Flow

class ChatFragmentViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val showMessageUseCase: ShowMessageUseCase,

    ) : ViewModel() {

    fun showMessages(): Flow<List<MessageModel>> = showMessageUseCase.invoke()

    fun sendMessage(message: MessageModel) {
        viewModelScope {
            sendMessageUseCase.invoke(message)
        }
    }
}