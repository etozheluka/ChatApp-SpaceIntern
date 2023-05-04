package com.space.chatapp.presentation.chat_screen.ui.chat_style_strategy

import android.view.View
import com.space.chatapp.R
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.utils.extension.setImageTint
import com.space.chatapp.utils.extension.setTextColorCompat
import com.space.chatapp.utils.extension.setTint

class SentMessageUiStrategy : MessageUiStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        with(binding) {
            with(R.color.purple_light) {
                dateTextViewTo.text = item.time.toString()
                dateTextViewTo.setTextColorCompat(R.color.grey_200)
                rectangleBigBubble.setImageTint(this)
                sendToTextView.setTint(this)
                rectangleSmallBubble.setImageTint(this)
            }
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }
}

class SentNoInternetMessageUiStrategy : MessageUiStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        with(binding) {
            with(R.color.fade_out_black) {
                dateTextViewTo.text = dateTextViewTo.context.getString(R.string.not_delivered)
                dateTextViewTo.setTextColorCompat(R.color.red_error)
                rectangleBigBubble.setImageTint(this)
                sendToTextView.setTint(this)
                rectangleSmallBubble.setImageTint(this)
            }
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }
}

class ReceivedMessageUiStrategy : MessageUiStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        with(binding) {
            with(R.color.darker_white) {
                dateTextViewTo.text = item.time.toString()
                dateTextViewTo.setTextColorCompat(R.color.grey_200)
                rectangleBigBubble.setImageTint(this)
                sendToTextView.setTint(this)
                rectangleSmallBubble.setImageTint(this)
            }
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }
}