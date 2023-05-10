package com.space.chatapp.presentation.base

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.space.chatapp.utils.DiffCallback

abstract class BaseChatAdapter<T : Any, VB : ViewBinding, VH : BaseChatAdapter.BaseViewHolder<T, VB>>
    : ListAdapter<T, VH>(DiffCallback<T>()) {


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    abstract class BaseViewHolder<T : Any, VB : ViewBinding>(binding: VB) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun onBind(item: T)
    }

}