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
            with(R.color.purple_light) {
                rectangleBigBubble.setImageTint(this)
                sendToTextView.setTint(this)
                rectangleSmallBubble.setImageTint(this)
            }
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }
}

class ReceivedMessageUiStrategy : MessageUiStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: MessageModel) {
        with(binding) {
            with(R.color.darker_white) {
                rectangleBigBubble.setImageTint(this)
                sendToTextView.setTint(this)
                rectangleSmallBubble.setImageTint(this)
            }
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }
}
