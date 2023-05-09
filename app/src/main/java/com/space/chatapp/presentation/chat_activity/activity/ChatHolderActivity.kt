package com.space.chatapp.presentation.chat_activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.space.chatapp.R
import com.space.chatapp.databinding.ActivityMainBinding
import com.space.chatapp.presentation.chat_activity.viewmodel.ChatHolderViewModel
import com.space.chatapp.presentation.chat_screen.ui.fragment.ChatFragment1
import com.space.chatapp.presentation.chat_screen.ui.fragment.ChatFragment2
import com.space.chatapp.utils.ChatThemeMode
import com.space.chatapp.utils.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * This activity is used to hold the fragments and maintain the theme state
 */

class ChatHolderActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModel<ChatHolderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        hideNavBar()
        if (savedInstanceState == null) {
            initFragments()
        }
        observeThemeMode()
        checkPreferencesStatus()
    }

    private fun checkPreferencesStatus() {
        launchWithLifecycle {
            viewModel.checkPreferencesStatus()
        }
    }

    private fun observeThemeMode() {
        launchWithLifecycle {
            viewModel.state.collect { mode ->
                val resourceId = when (mode) {
                    ChatThemeMode.DAY_MODE -> R.drawable.day_night_switch
                    ChatThemeMode.NIGHT_MODE -> R.drawable.night_day_switch
                }
                with(binding) {
                    dayNightSwitcher.getDrawable(baseContext, resourceId)
                    dayNightSwitcher.setOnClickListener {
                        viewModel.themeUpdate()
                    }
                }
            }
        }
    }

    private fun hideNavBar() {
        supportActionBar?.hide()
    }

    private fun initFragments() {
        setFragmentToContainer(binding.fragmentContainerFirst, ChatFragment1())
        setFragmentToContainer(binding.fragmentContainerSecond, ChatFragment2())
    }
}