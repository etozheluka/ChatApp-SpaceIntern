package com.space.chatapp.presentation.chat_screen.ui.chat_style

import android.view.View
import com.space.chatapp.R
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.model.ChatUser
import com.space.chatapp.utils.extension.setImageTint
import com.space.chatapp.utils.extension.setTint

class ChatStyleConfiguratorImpl(private val sender: ChatUser) : ChatStyleConfigurator {

    override fun applyStyleToViewHolder(binding: ChatMessageViewBinding, item: MessageModel) {
        val color =
            if (sender.name == item.sender!!.name) R.color.purple_light else R.color.darker_white
        with(binding) {
            rectangleBigBubble.setImageTint(color)
            sendToTextView.setTint(color)
            rectangleSmallBubble.setImageTint(color)
            val layoutDir =
                if (sender.name == item.sender.name) View.LAYOUT_DIRECTION_LTR else View.LAYOUT_DIRECTION_RTL
            root.layoutDirection = layoutDir
        }
    }
}

