package com.space.chatapp.presentation.chat_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.usecase.message.send.SendMessageUseCase
import com.space.chatapp.domain.usecase.message.show.ShowMessageUseCase
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.presentation.model.mapper.MessageDomainUIMapper
import com.space.chatapp.presentation.model.mapper.MessageUIDomainMapper
import com.space.chatapp.utils.extension.convertTimeToPattern
import com.space.chatapp.utils.extension.getTimeInMills
import com.space.chatapp.utils.extension.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * ViewModel for chat screen
 */

class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val showMessageUseCase: ShowMessageUseCase,
    private val uiDomainMapper: MessageUIDomainMapper,
    private val domainUiMapper: MessageDomainUIMapper
) : ViewModel() {


    fun showMessages(userId: String): Flow<List<Message>> = showMessageUseCase.invoke(userId)
        .map { messageModels ->
            messageModels.map { domainUiMapper(it) }
        }


    fun sendMessage(editTextInput: String, sender: String,isOnline:Boolean) {
        if (editTextInput.isNotEmpty()) {
            viewModelScope {
                val message = Message(
                    sender = sender,
                    message = editTextInput,
                    time = getTimeInMills().convertTimeToPattern(),
                    isOnline = isOnline
                )
                sendMessageUseCase.invoke(uiDomainMapper(message))
            }
        }
    }
}