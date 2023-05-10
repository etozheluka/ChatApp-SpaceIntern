package com.space.chatapp.utils.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

fun AppCompatActivity.setFragmentToContainer(container: FragmentContainerView, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction().apply {
        if (savedStateRegistry.isRestored) {
            replace(container.id, fragment, tag)
        }else{
            add(container.id, fragment, tag)
        }
    }.commit()
}