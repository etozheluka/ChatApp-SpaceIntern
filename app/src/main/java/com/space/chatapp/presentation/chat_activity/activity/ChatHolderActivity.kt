package com.space.chatapp.presentation.chat_activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.space.chatapp.R
import com.space.chatapp.databinding.ActivityMainBinding
import com.space.chatapp.presentation.chat_activity.viewmodel.ChatHolderViewModel
import com.space.chatapp.presentation.chat_screen.ui.ChatFragment
import com.space.chatapp.presentation.model.ChatScreenId
import com.space.chatapp.utils.ChatThemeMode
import com.space.chatapp.utils.extension.getDrawable
import com.space.chatapp.utils.extension.launchWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatHolderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<ChatHolderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideNavBar()
        initFragments()
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
        supportFragmentManager.beginTransaction().apply {
            replace(binding.topFragment.id, ChatFragment(), ChatScreenId.FIRST_USER_ID)
            replace(binding.bottomFragment.id, ChatFragment(), ChatScreenId.SECOND_USER_ID)
            commit()
        }
    }
}