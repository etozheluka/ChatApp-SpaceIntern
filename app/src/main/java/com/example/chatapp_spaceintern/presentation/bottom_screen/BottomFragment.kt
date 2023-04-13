package com.example.chatapp_spaceintern.presentation.bottom_screen

import androidx.fragment.app.viewModels
import com.example.chatapp_spaceintern.databinding.FragmentBottomBinding
import com.example.chatapp_spaceintern.presentation.base.BaseFragment

class BottomFragment : BaseFragment<FragmentBottomBinding>(FragmentBottomBinding::inflate) {

    private val viewModel: BottomViewModel by viewModels()
    override fun onBind() {

    }
}