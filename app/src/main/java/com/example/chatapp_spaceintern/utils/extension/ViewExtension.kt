package com.example.chatapp_spaceintern.utils.extension

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat

fun View.setColor(context: Context, color: Int) {
    setBackgroundColor(ContextCompat.getColor(context, color))
}