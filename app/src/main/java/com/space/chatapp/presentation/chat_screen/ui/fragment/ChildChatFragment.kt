package com.space.chatapp.presentation.chat_screen.ui.fragment


import com.space.chatapp.presentation.chat_screen.ui.base.BaseChatFragment

class ChatFragment1 : BaseChatFragment(){
    override fun userId(): String = "firstFragment"
}
class ChatFragment2 : BaseChatFragment(){
    override fun userId(): String = "secondFragment"
}