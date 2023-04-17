package com.example.chatapp_spaceintern.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.chatapp_spaceintern.domain.model.MessageModel

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
) : Fragment() {

    private var _binding: VB? = null
    abstract fun inflate(): Inflate<VB>
    protected val binding get() = _binding

    abstract fun onBind()
    abstract fun initRecycler()
    abstract fun showMessages()
    abstract fun sendMessage(messageModel: MessageModel)
    abstract fun saveMessageModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = this.inflate().invoke(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}