package com.space.chatapp.presentation.chat_screen.ui.chat_style

import android.view.View
import com.space.chatapp.R
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel

interface ChatStyleConfigurator {
    fun applyStyle(binding: ChatMessageViewBinding, item: MessageModel)
}

interface ChatStyleStrategy {
    fun getColor(): Int
    fun getLayoutDirection(): Int
}


class SelfChatStyleStrategy : ChatStyleStrategy {
    override fun getColor(): Int {
        return R.color.purple_light
    }

    override fun getLayoutDirection(): Int {
        return View.LAYOUT_DIRECTION_LTR
    }
}

class OtherChatStyleStrategy : ChatStyleStrategy {
    override fun getColor(): Int {
        return R.color.darker_white
    }

    override fun getLayoutDirection(): Int {
        return View.LAYOUT_DIRECTION_RTL
    }
}
