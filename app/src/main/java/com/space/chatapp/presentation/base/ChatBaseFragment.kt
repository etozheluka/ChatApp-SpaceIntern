package com.space.chatapp.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.space.chatapp.presentation.chat_screen.ui.adapter.AdapterListener
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass


abstract class BaseFragment<VM : ViewModel>(@LayoutRes layout: Int) : Fragment(layout) {

    protected val listener = object : AdapterListener {
        override fun getUserId(): String = userId()
    }
    abstract val viewModelClass: KClass<VM>
    private val viewModel: VM by viewModelForClass(clazz = viewModelClass)

    abstract fun userId(): String

    abstract fun onBindViewModel(viewModel: VM)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindViewModel(viewModel)
    }

}