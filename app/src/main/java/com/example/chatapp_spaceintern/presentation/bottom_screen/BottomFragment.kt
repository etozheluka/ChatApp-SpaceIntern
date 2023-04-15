package com.example.chatapp_spaceintern.presentation.bottom_screen

import androidx.fragment.app.viewModels
import com.example.chatapp_spaceintern.databinding.FragmentBottomBinding
import com.example.chatapp_spaceintern.presentation.base.BaseFragment
import com.example.chatapp_spaceintern.presentation.base.Inflate

class BottomFragment : BaseFragment<FragmentBottomBinding>() {

    private val viewModel: BottomViewModel by viewModels()
    override fun onBind() {}
    override fun inflate(): Inflate<FragmentBottomBinding> {
        return FragmentBottomBinding::inflate
    }
}