package com.space.chatapp.presentation.chat_screen.ui

import com.space.chatapp.R
import com.space.chatapp.databinding.FragmentChatBinding
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.base.BaseFragment
import com.space.chatapp.presentation.base.Inflate
import com.space.chatapp.presentation.chat_screen.adapter.ChatRecyclerAdapter
import com.space.chatapp.presentation.chat_screen.viewmodel.ChatViewModel
import com.space.chatapp.presentation.model.UserEnum
import com.space.chatapp.utils.extension.getCurrentTime
import com.space.chatapp.utils.extension.isNetworkAvailable
import com.space.chatapp.utils.extension.lifecycleScope
import com.space.chatapp.utils.extension.toastMessage
import kotlinx.coroutines.flow.flow
import kotlin.reflect.KClass


class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>() {

    override val viewModelClass: KClass<ChatViewModel>
        get() = ChatViewModel::class

    private val adapter by lazy {
        if (tag == UserEnum.TOP_USER.user) ChatRecyclerAdapter(UserEnum.TOP_USER.user) else ChatRecyclerAdapter(
            UserEnum.BOTTOM_USER.user
        )
    }

    override fun inflate(): Inflate<FragmentChatBinding> {
        return FragmentChatBinding::inflate
    }

    override fun onBindViewModel(viewModel: ChatViewModel) {

        initRecycler(viewModel)
        binding.imageBtnView.setOnClickListener {
            if (requireContext().isNetworkAvailable()) {
                saveMessageModel(viewModel)
            } else {
                sendNoInternetMessage()
            }
        }

    }

    private fun saveMessageModel(viewModel: ChatViewModel) {
        viewModel.sendMessage(provideMessageModel(binding.inputEditText.text.toString()))
        binding.inputEditText.text?.clear()
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

    private fun provideMessageModel(text: String) = MessageModel(
        id = null,
        sender = if (tag == UserEnum.TOP_USER.user) UserEnum.TOP_USER.user else UserEnum.BOTTOM_USER.user,
        message = text,
        time = getCurrentTime()
    )

    private fun sendNoInternetMessage() {
        requireContext().toastMessage(getString(R.string.check_your_internet))
        val flow = flow {
            emit(provideMessageModel(binding.inputEditText.text.toString()))
        }
        lifecycleScope {
            flow.collect {
                adapter.submitList(listOf(it))
            }
        }

    }
}
