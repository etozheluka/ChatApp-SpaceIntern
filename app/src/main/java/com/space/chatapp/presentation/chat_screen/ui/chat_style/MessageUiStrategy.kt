package com.space.chatapp.presentation.chat_screen.ui.chat_style

import android.view.View
import com.space.chatapp.R
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.utils.extension.setImageTint
import com.space.chatapp.utils.extension.setTint

interface MessageUiStrategy {
    fun setUiElements(binding: ChatMessageViewBinding, item: MessageModel)
}

class SentMessageUiStrategy : MessageUiStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: MessageModel) {
        with(binding) {
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
            rectangleBigBubble.setImageTint(R.color.purple_light)
            sendToTextView.setTint(R.color.purple_light)
            rectangleSmallBubble.setImageTint(R.color.purple_light)
        }
    }
}

class ReceivedMessageUiStrategy : MessageUiStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: MessageModel) {
        with(binding) {
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
            rectangleBigBubble.setImageTint(R.color.darker_white)
            sendToTextView.setTint(R.color.darker_white)
            rectangleSmallBubble.setImageTint(R.color.darker_white)
        }
    }
}
