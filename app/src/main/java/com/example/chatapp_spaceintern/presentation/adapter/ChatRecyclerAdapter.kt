package com.example.chatapp_spaceintern.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp_spaceintern.databinding.SendFromMessageBinding
import com.example.chatapp_spaceintern.databinding.SendToMessageBinding
import com.example.chatapp_spaceintern.domain.model.MessageModel
import com.example.chatapp_spaceintern.presentation.base.DiffCallback

class ChatRecyclerAdapter(private val sender: String) :
    ListAdapter<MessageModel, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        val messageSender = getItem(position)
        val returnVariable = when (messageSender.sender) {
            sender -> {
                TOP
            }
            else -> {
                BOTTOM
            }
        }
        return returnVariable
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TOP -> {
                TopViewHolder(
                    SendToMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> {
                BottomViewHolder(
                    SendFromMessageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopViewHolder -> holder.bind()
            is BottomViewHolder -> holder.bind()
        }
    }

    inner class TopViewHolder(private val binding: SendToMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentMessage: MessageModel
        fun bind() {
            currentMessage = getItem(adapterPosition)
            with(binding) {
                sendToTextView.text = currentMessage.message
                dateTextViewTo.text = currentMessage.time
            }
        }
    }

    inner class BottomViewHolder(private val binding: SendFromMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentMessage: MessageModel
        fun bind() {
            currentMessage = getItem(adapterPosition)
            with(binding) {
                sendFromTextView.text = currentMessage.message
                dateTextViewFrom.text = currentMessage.time
            }
        }
    }

    companion object {
        private const val TOP = 0
        private const val BOTTOM = 1
    }
}