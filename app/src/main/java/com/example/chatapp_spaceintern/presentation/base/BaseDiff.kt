package com.example.chatapp_spaceintern.presentation.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiff<T> {

    abstract val inner: T
    abstract val uniqueValue: Any
    abstract fun compareTo(other: Any?): Boolean
    override fun equals(other: Any?): Boolean {
        return compareTo(other)
    }

    override fun hashCode(): Int {
        var result = inner?.hashCode() ?: 0
        result = 31 * result + uniqueValue.hashCode()
        return result
    }
}


class DiffCallback<T : BaseDiff<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.uniqueValue == newItem.uniqueValue
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}