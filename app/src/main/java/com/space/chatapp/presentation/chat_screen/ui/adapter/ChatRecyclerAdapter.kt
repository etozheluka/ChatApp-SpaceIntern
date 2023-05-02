package com.space.chatapp.presentation.chat_screen.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.base.AdapterListener
import com.space.chatapp.presentation.base.BaseChatAdapter
import com.space.chatapp.presentation.chat_screen.ui.chat_style.ReceivedMessageUiStrategy
import com.space.chatapp.presentation.chat_screen.ui.chat_style.SentMessageUiStrategy
import com.space.chatapp.presentation.chat_screen.ui.chat_style.SentNoInternetMessageUiStrategy


class ChatRecyclerAdapter(private val listener: AdapterListener) :
    BaseChatAdapter<MessageModel, ChatMessageViewBinding, ChatRecyclerAdapter.ChatViewHolder>(
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
            ), listener
        )
    }

    class ChatViewHolder(
        private val binding: ChatMessageViewBinding,
        private val listener: AdapterListener
    ) :
        BaseViewHolder<MessageModel, ChatMessageViewBinding>(binding) {

        override fun onBind(item: MessageModel) {
            with(binding) {
                sendToTextView.text = item.message

                val uiStrategy = if (listener.getUserId() == item.sender) {
                    if (item.isOnline) SentMessageUiStrategy() else SentNoInternetMessageUiStrategy()
                } else {
                    ReceivedMessageUiStrategy()
                }
                uiStrategy.setUiElements(binding, item)
            }
        }
    }
}

