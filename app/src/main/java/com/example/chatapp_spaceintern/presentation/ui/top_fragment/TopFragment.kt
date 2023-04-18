package com.example.chatapp_spaceintern.presentation.ui.top_fragment

import androidx.lifecycle.lifecycleScope
import com.example.chatapp_spaceintern.R
import com.example.chatapp_spaceintern.databinding.FragmentTopBinding
import com.example.chatapp_spaceintern.domain.model.MessageModel
import com.example.chatapp_spaceintern.presentation.adapter.ChatRecyclerAdapter
import com.example.chatapp_spaceintern.presentation.base.BaseFragment
import com.example.chatapp_spaceintern.presentation.base.Inflate
import com.example.chatapp_spaceintern.utils.extension.currentTime
import com.example.chatapp_spaceintern.utils.extension.isNetworkAvailable
import com.example.chatapp_spaceintern.utils.extension.toastMessage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class TopFragment() : BaseFragment<FragmentTopBinding>() {

    private val viewModel by viewModel<TopFragmentViewModel>()

    private val adapter by lazy {
        ChatRecyclerAdapter(SENDER)
    }

    override fun inflate(): Inflate<FragmentTopBinding> {
        return FragmentTopBinding::inflate
    }

    override fun onBind() {
        initRecycler()
        binding.imageBtnView.setOnClickListener {
            saveMessageModel()
        }
    }

    override fun saveMessageModel() {
        if(requireContext().isNetworkAvailable()){
            sendMessage(
                MessageModel(
                    id = null,
                    sender = SENDER,
                    message = binding.inputEditText.text.toString(),
                    time = currentTime()
                )
            )
        }else{
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

    companion object {
        private const val SENDER = "TOP"
    }
}
