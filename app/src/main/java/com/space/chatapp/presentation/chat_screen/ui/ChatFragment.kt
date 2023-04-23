package com.space.chatapp.presentation.chat_screen.ui

import com.space.chatapp.databinding.FragmentChatBinding
import com.space.chatapp.presentation.base.BaseFragment
import com.space.chatapp.presentation.base.Inflate
import com.space.chatapp.presentation.chat_screen.adapter.ChatRecyclerAdapter
import com.space.chatapp.presentation.chat_screen.viewmodel.ChatViewModel
import com.space.chatapp.presentation.model.UserEnum
import com.space.chatapp.utils.extension.isNetworkAvailable
import com.space.chatapp.utils.extension.lifecycleScope
import kotlin.reflect.KClass


class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>() {

    override fun inflate(): Inflate<FragmentChatBinding> {
        return FragmentChatBinding::inflate
    }

    override val viewModelClass: KClass<ChatViewModel>
        get() = ChatViewModel::class

    private val adapter by lazy {
        ChatRecyclerAdapter(UserEnum.valueOf(tag!!))

    }

    override fun onBindViewModel(viewModel: ChatViewModel) {
        with(viewModel) {
            initRecycler(this)
            binding.imageBtnView.setOnClickListener {
                if (requireContext().isNetworkAvailable()) {
                    saveMessageModel(viewModel)
                } else {
                    sendNoInternetMessage(viewModel)
                }
                binding.inputEditText.text?.clear()
            }
        }
    }

    private fun saveMessageModel(viewModel: ChatViewModel) {
        with(viewModel) {
            sendMessage(
                binding.inputEditText.text.toString(), tag.toString()
            )
        }
    }

    private fun sendNoInternetMessage(viewModel: ChatViewModel) {
        lifecycleScope {
            viewModel.sendNoInternetMessage(binding.inputEditText.text.toString(), tag.toString())
            viewModel.messages.collect {
                adapter.submitList(listOf(it))
            }
        }
    }

    private fun initRecycler(viewModel: ChatViewModel) {
        binding.topRecycler.adapter = adapter
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
