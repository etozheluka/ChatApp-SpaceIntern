package com.space.chatapp.utils.extension


fun String?.ifEmpty(): Boolean {
    return this == null || this.isEmpty()
}