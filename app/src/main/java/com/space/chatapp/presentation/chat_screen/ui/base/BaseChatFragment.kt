package com.space.chatapp.presentation.chat_screen.ui.base

import com.space.chatapp.R
import com.space.chatapp.databinding.FragmentChatBinding
import com.space.chatapp.presentation.base.BaseFragment
import com.space.chatapp.presentation.chat_screen.ui.adapter.AdapterListener
import com.space.chatapp.presentation.chat_screen.ui.adapter.ChatRecyclerAdapter
import com.space.chatapp.presentation.chat_screen.viewmodel.ChatViewModel
import com.space.chatapp.utils.extension.isNetworkAvailable
import com.space.chatapp.utils.extension.lifecycleScope
import com.space.chatapp.utils.extension.viewBinding
import kotlin.reflect.KClass

open class BaseChatFragment : BaseFragment<ChatViewModel>() {

    private val listener = object : AdapterListener {
        override fun getUserId(): String = userId()
    }

    private val binding by viewBinding(FragmentChatBinding::bind)

    override val layout: Int
        get() = R.layout.fragment_chat


    override val viewModelClass: KClass<ChatViewModel>
        get() = ChatViewModel::class

    override fun userId(): String = userId()


    override fun onBindViewModel(viewModel: ChatViewModel) {
        with(viewModel) {
            initRecycler(this)
            binding.sendBtn.setOnClickListener {
                saveMessageModel(this)
                binding.inputEditText.text?.clear()
            }
        }
    }

    private fun saveMessageModel(viewModel: ChatViewModel) {
        viewModel.sendMessage(
            binding.inputEditText.text.toString(),
            userId(),
            requireContext().isNetworkAvailable()
        )
    }

    private fun initRecycler(viewModel: ChatViewModel) {
        binding.chatRecycler.adapter = adapter
        showMessages(viewModel)
    }

    private fun showMessages(viewModel: ChatViewModel) {
        lifecycleScope {
            viewModel.showMessages().collect {
                adapter.submitList(viewModel.filterMessages(it, userId()))
            }
        }
    }

    private val adapter by lazy {
        ChatRecyclerAdapter(listener)
    }

}