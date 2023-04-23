package com.space.chatapp.utils.extension

import android.widget.EditText

fun EditText.ifNotEmpty(action: (String) -> Unit) {
    val text = this.text.toString().trim()
    if (text.isNotEmpty()) {
        action(text)
    }
}