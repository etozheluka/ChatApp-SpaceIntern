package com.space.chatapp.presentation.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseChatAdapter<T : Any, VB : ViewBinding, VH : BaseChatAdapter.BaseViewHolder<T, VB>>(
    private val listener: AdapterListener
) : ListAdapter<T, VH>(DiffCallback<T>()) {

    interface AdapterListener {
        fun getUserId(): String
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), listener)
    }


    abstract class BaseViewHolder<T : Any, VB : ViewBinding>(binding: VB) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun onBind(item: T, listener: AdapterListener)
    }

    class DiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem === newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}