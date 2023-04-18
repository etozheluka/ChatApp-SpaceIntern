package com.example.chatapp_spaceintern.presentation.ui.bottom_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp_spaceintern.domain.model.MessageModel
import com.example.chatapp_spaceintern.domain.use_case.SendMessageUseCase
import com.example.chatapp_spaceintern.domain.use_case.ShowMessageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BottomFragmentViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val showMessageUseCase: ShowMessageUseCase,

) : ViewModel() {

    fun showMessages(): Flow<List<MessageModel>> = showMessageUseCase.invoke()

    fun sendMessage(message: MessageModel) {
        viewModelScope.launch {
            sendMessageUseCase.invoke(message)
        }
    }
}