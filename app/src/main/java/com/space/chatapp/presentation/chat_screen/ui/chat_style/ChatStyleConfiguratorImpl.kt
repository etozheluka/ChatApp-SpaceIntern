package com.space.chatapp.presentation.chat_screen.ui.chat_style

import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.model.ChatUser
import com.space.chatapp.utils.extension.setImageTint
import com.space.chatapp.utils.extension.setTint

class ChatStyleConfiguratorImpl(private val sender: ChatUser) : ChatStyleConfigurator {

    override fun applyStyle(binding: ChatMessageViewBinding, item: MessageModel) {

        val styleStrategy = when (sender.name) {
            item.sender!!.name -> SelfChatStyleStrategy()
            else -> OtherChatStyleStrategy()
        }
        val color = styleStrategy.getColor()
        with(binding) {
            rectangleBigBubble.setImageTint(color)
            sendToTextView.setTint(color)
            rectangleSmallBubble.setImageTint(color)
            root.layoutDirection = styleStrategy.getLayoutDirection()
        }
    }
}