package com.space.chatapp.presentation.chat_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.chat_screen.ui.chat_style.ChatStyleConfigurator
import com.space.chatapp.utils.DiffCallback
import com.space.chatapp.utils.extension.convertTimeToPattern

class ChatRecyclerAdapter(private val styleConfigurator: ChatStyleConfigurator) :
    ListAdapter<MessageModel, ChatRecyclerAdapter.ChatViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            ChatMessageViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            styleConfigurator
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChatViewHolder(private val binding: ChatMessageViewBinding, private val styleConfigurator: ChatStyleConfigurator) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MessageModel) = with(binding) {
            sendToTextView.text = item.message
            dateTextViewTo.text = item.time!!.convertTimeToPattern()
            styleConfigurator.applyStyleToViewHolder(binding, item)
        }
    }
}