package com.space.chatapp.presentation.chat_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.base.BaseChatAdapter
import com.space.chatapp.presentation.chat_screen.ui.chat_style.ReceivedMessageUiStrategy
import com.space.chatapp.presentation.chat_screen.ui.chat_style.SentMessageUiStrategy
import com.space.chatapp.utils.extension.convertTimeToPattern


class ChatRecyclerAdapter(listener: AdapterListener) :
    BaseChatAdapter<MessageModel, ChatMessageViewBinding, ChatRecyclerAdapter.ChatViewHolder>(
        listener
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        return ChatViewHolder(
            ChatMessageViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ChatViewHolder(private val binding: ChatMessageViewBinding) :
        BaseViewHolder<MessageModel, ChatMessageViewBinding>(binding) {

        override fun onBind(item: MessageModel, listener: AdapterListener) {
            with(binding) {
                sendToTextView.text = item.message
                dateTextViewTo.text = item.time!!.convertTimeToPattern()
                val uiStrategy = if (listener.getUserId() == item.sender) {
                    SentMessageUiStrategy()
                } else {
                    ReceivedMessageUiStrategy()
                }
                uiStrategy.setUiElements(binding, item)
            }
        }
    }
}

