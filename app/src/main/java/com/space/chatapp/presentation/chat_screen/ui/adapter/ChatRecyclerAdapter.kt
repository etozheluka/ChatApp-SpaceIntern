package com.space.chatapp.presentation.chat_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.chatapp.R
import com.space.chatapp.databinding.SendMessageBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.model.ChatUser
import com.space.chatapp.utils.DiffCallback
import com.space.chatapp.utils.extension.convertTimeToPattern
import com.space.chatapp.utils.extension.setImageTint
import com.space.chatapp.utils.extension.setTint

class ChatRecyclerAdapter(private val sender: ChatUser) :
    ListAdapter<MessageModel, ChatRecyclerAdapter.ChatViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            SendMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(sender, getItem(position))
    }

    class ChatViewHolder(private val binding: SendMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sender: ChatUser, item: MessageModel) = with(binding) {
            sendToTextView.text = item.message
            dateTextViewTo.text = item.time!!.convertTimeToPattern()
            val color =
                if (sender.name == item.sender!!.name) R.color.purple_light else  R.color.darker_white
            rectangleImageView.setImageTint(color)
            sendToTextView.setTint(color)
            root.scaleX = if (sender.name == item.sender.name) SCALE_FACTOR else FLIPPED_SCALE_FACTOR
            sendToTextView.scaleX = root.scaleX
            dateTextViewTo.scaleX = root.scaleX
        }
    }

    companion object {
        private const val SCALE_FACTOR = 1f
        private const val FLIPPED_SCALE_FACTOR = -1f
    }
}