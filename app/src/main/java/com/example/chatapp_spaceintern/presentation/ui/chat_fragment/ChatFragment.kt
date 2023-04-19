package com.example.chatapp_spaceintern.presentation.ui.chat_fragment

import androidx.lifecycle.lifecycleScope
import com.example.chatapp_spaceintern.R
import com.example.chatapp_spaceintern.databinding.FragmentChatBinding
import com.example.chatapp_spaceintern.domain.model.MessageModel
import com.example.chatapp_spaceintern.presentation.adapter.ChatRecyclerAdapter
import com.example.chatapp_spaceintern.presentation.base.BaseFragment
import com.example.chatapp_spaceintern.presentation.base.Inflate
import com.example.chatapp_spaceintern.presentation.model.UserEnum
import com.example.chatapp_spaceintern.utils.extension.currentTime
import com.example.chatapp_spaceintern.utils.extension.isNetworkAvailable
import com.example.chatapp_spaceintern.utils.extension.toastMessage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChatFragment() : BaseFragment<FragmentChatBinding, ChatFragmentViewModel>() {

    override val viewModel by viewModel<ChatFragmentViewModel>()

    private val adapter by lazy {
        if (tag == UserEnum.TOP_USER.user) ChatRecyclerAdapter(UserEnum.TOP_USER.user) else ChatRecyclerAdapter(
            UserEnum.BOTTOM_USER.user
        )
    }

    override fun inflate(): Inflate<FragmentChatBinding> {
        return FragmentChatBinding::inflate
    }


    override fun onBind(viewModel: ChatFragmentViewModel) {
        initRecycler()
        binding.imageBtnView.setOnClickListener {
            saveMessageModel()
        }
    }


    override fun saveMessageModel() {
        if (requireContext().isNetworkAvailable()) {
            sendMessage(
                MessageModel(
                    id = null,
                    sender = if (tag == UserEnum.TOP_USER.user) UserEnum.TOP_USER.user else UserEnum.BOTTOM_USER.user,
                    message = binding.inputEditText.text.toString(),
                    time = currentTime()
                )
            )
        } else {
            requireContext().toastMessage(getString(R.string.check_your_internet))
        }

        binding.inputEditText.text?.clear()
    }

    override fun initRecycler() {
        binding.topRecycler.adapter = adapter
        showMessages()
    }

    override fun showMessages() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.showMessages().collect {
                adapter.submitList(it)
            }
        }
    }

    override fun sendMessage(messageModel: MessageModel) {
        viewModel.sendMessage(messageModel)
    }

}
