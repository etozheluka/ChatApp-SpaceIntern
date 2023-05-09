package com.space.chatapp.utils.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

fun AppCompatActivity.setFragmentToContainer(container: FragmentContainerView, fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        if (savedStateRegistry.isRestored) {
            replace(container.id, fragment)
        }else{
            add(container.id, fragment)
        }
    }.commit()
}