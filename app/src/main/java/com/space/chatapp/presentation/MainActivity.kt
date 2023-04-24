package com.space.chatapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.space.chatapp.R
import com.space.chatapp.databinding.ActivityMainBinding
import com.space.chatapp.presentation.model.UserEnum
import com.space.chatapp.presentation.chat_screen.ui.ChatFragment
import com.space.chatapp.utils.ThemeModeEnum
import com.space.chatapp.utils.extension.getDrawable
import com.space.chatapp.utils.extension.launchWithLifecycle
import com.space.chatapp.utils.toAppCompatMode
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideNavBar()
        initFragments(savedInstanceState)
        initDayNightSwitcher()
        observeDayNightMode()
        checkPreferencesStatus()
    }

    private fun hideNavBar() {
        supportActionBar?.hide()
    }

    private fun initFragments(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().apply {
            if (savedInstanceState == null) {
                add(binding.topFragment.id, ChatFragment(), UserEnum.TOP_USER.name)
                add(binding.bottomFragment.id, ChatFragment(), UserEnum.BOTTOM_USER.name)
            } else {
                replace(binding.topFragment.id, ChatFragment(), UserEnum.TOP_USER.name)
                replace(binding.bottomFragment.id, ChatFragment(), UserEnum.BOTTOM_USER.name)
            }
                .commit()
        }
    }

    private fun checkPreferencesStatus() {
        launchWithLifecycle {
            viewModel.checkPreferencesStatus()

        }
    }

    private fun observeDayNightMode() {
        launchWithLifecycle {
            viewModel.state.collect { mode ->
                setDayNightMode(mode)
            }
        }
    }

    private fun initDayNightSwitcher() {
        val resourceId =
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                R.drawable.night_day_switch
            } else {
                R.drawable.day_night_switch
            }
        with(binding){
            dayNightSwitcher.getDrawable(baseContext, resourceId)
            dayNightSwitcher.setOnClickListener {
                viewModel.dayNightHandling()
            }
        }
    }

    private fun setDayNightMode(mode: ThemeModeEnum) {
        val resourceId = when (mode) {
            ThemeModeEnum.DAY_MODE -> R.drawable.day_night_switch
            ThemeModeEnum.NIGHT_MODE -> R.drawable.night_day_switch
        }
        binding.dayNightSwitcher.getDrawable(baseContext, resourceId)
        AppCompatDelegate.setDefaultNightMode(mode.toAppCompatMode())
    }
}