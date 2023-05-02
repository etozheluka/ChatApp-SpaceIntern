package com.space.chatapp.presentation.chat_screen.ui

import com.space.chatapp.databinding.FragmentChatBinding
import com.space.chatapp.presentation.base.BaseFragment
import com.space.chatapp.presentation.base.Inflate
import com.space.chatapp.presentation.chat_screen.ui.adapter.ChatRecyclerAdapter
import com.space.chatapp.presentation.chat_screen.viewmodel.ChatViewModel
import com.space.chatapp.utils.extension.isNetworkAvailable
import com.space.chatapp.utils.extension.lifecycleScope
import kotlin.reflect.KClass

class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>() {

    override fun inflate(): Inflate<FragmentChatBinding> {
        return FragmentChatBinding::inflate
    }

    override fun userId(): String = tag.toString()

    override val viewModelClass: KClass<ChatViewModel>
        get() = ChatViewModel::class

    private val adapter by lazy {
        ChatRecyclerAdapter(listener)
    }

    override fun onBindViewModel(viewModel: ChatViewModel) {
        with(viewModel) {
            initRecycler(this)
            binding.sendBtn.setOnClickListener {
                if (requireContext().isNetworkAvailable()) {
                    saveMessageModel(this)
                } else {
                    sendNoInternetMessage(this)
                }
                binding.inputEditText.text?.clear()
            }
        }
    }

    private fun saveMessageModel(viewModel: ChatViewModel) {
        viewModel.sendMessage(
            binding.inputEditText.text.toString(), tag.toString()
        )
    }

    private fun sendNoInternetMessage(viewModel: ChatViewModel) {
        lifecycleScope {
            with(viewModel) {
                sendNoInternetMessage(
                    binding.inputEditText.text.toString(),
                    tag.toString()
                )
                messages.collect { adapter.submitList(listOf(it)) }
            }
        }
    }

    private fun initRecycler(viewModel: ChatViewModel) {
        binding.chatRecycler.adapter = adapter
        showMessages(viewModel)
    }

    private fun showMessages(viewModel: ChatViewModel) {
        lifecycleScope {
            viewModel.showMessages().collect {
                adapter.submitList(it)
            }
        }
    }
}
