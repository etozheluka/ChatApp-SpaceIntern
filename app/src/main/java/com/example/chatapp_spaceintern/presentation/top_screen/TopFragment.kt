package com.example.chatapp_spaceintern.presentation.top_screen

import androidx.fragment.app.viewModels
import com.example.chatapp_spaceintern.databinding.FragmentTopBinding
import com.example.chatapp_spaceintern.presentation.base.BaseFragment

class TopFragment : BaseFragment<FragmentTopBinding>(FragmentTopBinding::inflate) {

    private val viewModel: TopViewModel by viewModels()
    override fun onBind() {}
}