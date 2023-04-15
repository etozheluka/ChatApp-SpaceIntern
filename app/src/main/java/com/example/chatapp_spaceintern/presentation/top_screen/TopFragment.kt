package com.example.chatapp_spaceintern.presentation.top_screen

import androidx.fragment.app.viewModels
import com.example.chatapp_spaceintern.databinding.FragmentTopBinding
import com.example.chatapp_spaceintern.presentation.base.BaseFragment
import com.example.chatapp_spaceintern.presentation.base.Inflate

class TopFragment : BaseFragment<FragmentTopBinding>() {

    private val viewModel: TopViewModel by viewModels()
    override fun inflate(): Inflate<FragmentTopBinding> {
        return FragmentTopBinding::inflate
    }

    override fun onBind() {}

}