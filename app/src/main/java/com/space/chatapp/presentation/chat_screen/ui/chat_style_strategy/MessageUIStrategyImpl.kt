package com.space.chatapp.presentation.chat_screen.ui.chat_style_strategy

import android.view.View
import com.space.chatapp.R
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.utils.extension.setImageTint
import com.space.chatapp.utils.extension.setTextColorCompat
import com.space.chatapp.utils.extension.setTint

private fun setCommonUiElements(
    binding: ChatMessageViewBinding,
    item: Message,
    color: Int
) {
    with(binding) {
        dateTextViewTo.text = item.time.toString()
        dateTextViewTo.setTextColorCompat(R.color.grey_200)
        rectangleBigBubble.setImageTint(color)
        sendToTextView.setTint(color)
        rectangleSmallBubble.setImageTint(color)
    }

}

class SentMessageUIStrategy : MessageUIStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        with(binding) {
            with(R.color.purple_light) {
                setCommonUiElements(
                    binding = binding,
                    item = item,
                    color = this
                )
            }
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }
}

class SentNoInternetMessageUIStrategy : MessageUIStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        with(binding) {
            with(R.color.purple_light) {
                setCommonUiElements(
                    binding = binding,
                    item = item,
                    color = this
                )
            }
            dateTextViewTo.text = dateTextViewTo.context.getString(R.string.not_delivered)
            dateTextViewTo.setTextColorCompat(R.color.red_error)
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }
}

class ReceivedMessageUIStrategy : MessageUIStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        with(binding) {
            with(R.color.darker_white) {
                setCommonUiElements(binding, item, this)
            }
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }
}