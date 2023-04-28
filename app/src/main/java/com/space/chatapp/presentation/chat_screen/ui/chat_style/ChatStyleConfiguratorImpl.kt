package com.space.chatapp.presentation.chat_screen.ui.chat_style

import androidx.core.view.forEach
import com.space.chatapp.R
import com.space.chatapp.databinding.SendMessageBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.model.ChatUser
import com.space.chatapp.utils.extension.setImageTint
import com.space.chatapp.utils.extension.setTint

class ChatStyleConfiguratorImpl(private val sender: ChatUser) : ChatStyleConfigurator {
    companion object {
        private const val SCALE_FACTOR = 1f
        private const val FLIPPED_SCALE_FACTOR = -1f
    }

    override fun applyStyleToViewHolder(binding: SendMessageBinding, item: MessageModel) {
        val color =
            if (sender.name == item.sender!!.name) R.color.purple_light else R.color.darker_white
        with(binding) {
            rectangleBigBubble.setImageTint(color)
            sendToTextView.setTint(color)
            rectangleSmallBubble.setImageTint(color)
            val scale = if (sender.name == item.sender.name) SCALE_FACTOR else FLIPPED_SCALE_FACTOR
            root.scaleX = scale
            root.forEach { child ->
                child.scaleX = scale
            }
        }
    }
}

