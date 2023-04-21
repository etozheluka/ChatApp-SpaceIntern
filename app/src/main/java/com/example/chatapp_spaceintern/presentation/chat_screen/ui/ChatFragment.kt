package com.example.chatapp_spaceintern.presentation.chat_screen.ui

import androidx.lifecycle.lifecycleScope
import com.example.chatapp_spaceintern.R
import com.example.chatapp_spaceintern.databinding.FragmentChatBinding
import com.example.chatapp_spaceintern.domain.model.MessageModel
import com.example.chatapp_spaceintern.presentation.base.BaseFragment
import com.example.chatapp_spaceintern.presentation.base.Inflate
import com.example.chatapp_spaceintern.presentation.chat_screen.adapter.ChatRecyclerAdapter
import com.example.chatapp_spaceintern.presentation.chat_screen.viewmodel.ChatFragmentViewModel
import com.example.chatapp_spaceintern.presentation.model.UserEnum
import com.example.chatapp_spaceintern.utils.extension.getCurrentTime
import com.example.chatapp_spaceintern.utils.extension.isNetworkAvailable
import com.example.chatapp_spaceintern.utils.extension.lifecycleScope
import com.example.chatapp_spaceintern.utils.extension.toastMessage
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass


class ChatFragment : BaseFragment<FragmentChatBinding, ChatFragmentViewModel>() {

    override val viewModelClass: KClass<ChatFragmentViewModel>
        get() = ChatFragmentViewModel::class

    private val adapter by lazy {
        if (tag == UserEnum.TOP_USER.user) ChatRecyclerAdapter(UserEnum.TOP_USER.user) else ChatRecyclerAdapter(
            UserEnum.BOTTOM_USER.user
        )
    }

    override fun inflate(): Inflate<FragmentChatBinding> {
        return FragmentChatBinding::inflate
    }

    override fun onBindViewModel(viewModel: ChatFragmentViewModel) {

        initRecycler(viewModel)
        binding.imageBtnView.setOnClickListener {
            if (requireContext().isNetworkAvailable()) {
                saveMessageModel(viewModel)
            } else {
                sendNoInternetMessage()
            }
        }

    }

    private fun saveMessageModel(viewModel: ChatFragmentViewModel) {
        viewModel.sendMessage(provideMessageModel(binding.inputEditText.text.toString()))
        binding.inputEditText.text?.clear()
    }

    private fun initRecycler(viewModel: ChatFragmentViewModel) {
        binding.topRecycler.adapter = adapter
        showMessages(viewModel)
    }

    private fun showMessages(viewModel: ChatFragmentViewModel) {
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
