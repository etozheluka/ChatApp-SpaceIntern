package com.space.chatapp.presentation.chat_screen.ui.chat_style

import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel

interface ChatStyleConfigurator {
    fun applyStyleToViewHolder(binding: ChatMessageViewBinding, item: MessageModel)
}