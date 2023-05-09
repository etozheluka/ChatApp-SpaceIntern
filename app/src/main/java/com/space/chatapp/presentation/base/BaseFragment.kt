package com.space.chatapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.space.chatapp.presentation.chat_screen.ui.adapter.AdapterListener
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

/** Base class for all fragments */

abstract class BaseFragment<VM : ViewModel> : Fragment() {

    protected abstract val layout: Int
    abstract val viewModelClass: KClass<VM>
    private val viewModel: VM by viewModelForClass(clazz = viewModelClass)

    abstract fun userId(): String
    abstract fun onBindViewModel(viewModel: VM)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindViewModel(viewModel)
    }

}