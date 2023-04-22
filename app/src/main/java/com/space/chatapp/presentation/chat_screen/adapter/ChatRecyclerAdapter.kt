package com.space.chatapp.presentation.chat_screen.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.chatapp.R
import com.space.chatapp.databinding.SendMessageBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.model.UserEnum
import com.space.chatapp.utils.DiffCallback
import com.space.chatapp.utils.extension.setTint


class ChatRecyclerAdapter(private val sender: UserEnum?) :
    ListAdapter<MessageModel, ChatRecyclerAdapter.ChatViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            SendMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        sender?.let { holder.bind(it, getItem(position)) }
    }

    class ChatViewHolder(private val binding: SendMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sender: UserEnum, item: MessageModel) = with(binding) {
            sendToTextView.text = item.message
            dateTextViewTo.text = item.time
            val color =
                if (sender.name == item.sender) R.color.purple_light else R.color.darker_white
            root.scaleX = if (sender.name == item.sender) 1f else -1f
            sendToTextView.scaleX = root.scaleX
            dateTextViewTo.scaleX = root.scaleX
            rectangleImageView.setTint(color)
            sendToTextView.setTint(color)
        }
    }
}