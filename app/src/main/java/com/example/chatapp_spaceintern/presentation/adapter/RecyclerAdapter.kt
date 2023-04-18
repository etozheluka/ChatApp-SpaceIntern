package com.example.chatapp_spaceintern.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp_spaceintern.databinding.SendFromMessageBinding
import com.example.chatapp_spaceintern.databinding.SendToMessageBinding
import com.example.chatapp_spaceintern.domain.model.MessageModel

class RecyclerAdapter(private val sender: String) :
    ListAdapter<MessageModel, RecyclerView.ViewHolder>(DiffCallBack()) {

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

    class DiffCallBack : DiffUtil.ItemCallback<MessageModel>() {
        override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class TopViewHolder(private val binding: SendToMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentMessage: MessageModel
        fun bind() {
            currentMessage = getItem(adapterPosition)
            binding.apply {
                binding.sendToTextView.text = currentMessage.message
                binding.dateTextViewTo.text = currentMessage.time
            }
        }
    }

    inner class BottomViewHolder(private val binding: SendFromMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentMessage: MessageModel
        fun bind() {
            currentMessage = getItem(adapterPosition)
            binding.apply {
                binding.sendFromTextView.text = currentMessage.message
                binding.dateTextViewFrom.text = currentMessage.time
            }
        }
    }

    companion object {
        private const val TOP = 0
        private const val BOTTOM = 1
    }
}