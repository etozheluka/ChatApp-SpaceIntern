package com.space.chatapp.presentation.chat_screen.ui.chat_style

import com.space.chatapp.databinding.SendMessageBinding
import com.space.chatapp.domain.model.MessageModel

interface ChatStyleConfigurator {
    fun applyStyleToViewHolder(binding: SendMessageBinding, item: MessageModel)
}