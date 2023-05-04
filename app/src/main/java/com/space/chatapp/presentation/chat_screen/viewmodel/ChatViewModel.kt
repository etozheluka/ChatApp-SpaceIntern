package com.space.chatapp.presentation.chat_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.usecase.message.send.SendMessageUseCase
import com.space.chatapp.domain.usecase.message.show.ShowMessageUseCase
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.utils.extension.convertTimeToPattern
import com.space.chatapp.utils.extension.getTimeInMills
import com.space.chatapp.utils.extension.viewModelScope
import com.space.chatapp.utils.mapper.toDomainModel
import com.space.chatapp.utils.mapper.toPresentationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val showMessageUseCase: ShowMessageUseCase,
) : ViewModel() {

    fun showMessages(): Flow<List<Message>> = showMessageUseCase.invoke()
        .map { messageModels ->
            messageModels.map { it.toPresentationModel() }
        }


    fun sendMessage(editTextInput: String, tag: String,isOnline:Boolean) {
        if (editTextInput.isNotEmpty()) {
            viewModelScope {
                val message = Message(
                    sender = tag,
                    message = editTextInput,
                    time = getTimeInMills().convertTimeToPattern(),
                    isOnline = isOnline
                )
                sendMessageUseCase.invoke(message.toDomainModel())
            }
        }
    }
}