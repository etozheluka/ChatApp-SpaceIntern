package com.space.chatapp.utils.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty
import kotlin.properties.ReadOnlyProperty

/**
 * ViewBinding Extension
 * */

inline fun <VB : ViewBinding> AppCompatActivity.viewBinding(crossinline factory: (LayoutInflater) -> VB) =
    lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }

fun <VB : ViewBinding> Fragment.viewBinding(factory: (View) -> VB): ReadOnlyProperty<Fragment, VB> =
    object : ReadOnlyProperty<Fragment, VB>, DefaultLifecycleObserver {
        private var binding: VB? = null

        override fun getValue(thisRef: Fragment, property: KProperty<*>): VB =
            binding ?: factory(requireView()).also {
                if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                    viewLifecycleOwner.lifecycle.addObserver(this)
                    binding = it
                }
            }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }
    }

inline fun <VB : ViewBinding> ViewGroup.viewBinding(factory: (LayoutInflater, ViewGroup, Boolean) -> VB) =
    factory(LayoutInflater.from(context), this, false)