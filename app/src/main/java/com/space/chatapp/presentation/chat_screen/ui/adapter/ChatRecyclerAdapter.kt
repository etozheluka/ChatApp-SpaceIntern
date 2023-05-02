package com.space.chatapp.presentation.chat_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.chat_screen.ui.chat_style.ReceivedMessageUiStrategy
import com.space.chatapp.presentation.chat_screen.ui.chat_style.SentMessageUiStrategy
import com.space.chatapp.presentation.chat_screen.ui.chat_style.SentNoInternetMessageUiStrategy
import com.space.chatapp.utils.DiffCallback
import com.space.chatapp.utils.extension.viewBinding


class ChatRecyclerAdapter(private val listener: AdapterListener) :
    ListAdapter<MessageModel, ChatRecyclerAdapter.ChatViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(parent.viewBinding(ChatMessageViewBinding::inflate))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.onBind(listener, getItem(position))
    }

    class ChatViewHolder(
        private val binding: ChatMessageViewBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(listener: AdapterListener, item: MessageModel) {
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

