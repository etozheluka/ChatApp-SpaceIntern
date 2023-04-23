package com.space.chatapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.space.chatapp.R
import com.space.chatapp.databinding.ActivityMainBinding
import com.space.chatapp.utils.ThemeModeEnum
import com.space.chatapp.presentation.model.UserEnum
import com.space.chatapp.presentation.chat_screen.ui.ChatFragment
import com.space.chatapp.utils.extension.getDrawable
import com.space.chatapp.utils.extension.launchWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideNavBar()
        checkPreferencesStatus()
        initFragments()
        checkDeviceStatus()

        binding.dayNightSwitcher.setOnClickListener {
            dayNightHandling()
        }
    }

    private fun dayNightMode() {
        binding.apply {
            dayNightSwitcher.getDrawable(baseContext, R.drawable.day_night_switch)
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    private fun nightDayMode() {
        binding.apply {
            dayNightSwitcher.getDrawable(baseContext, R.drawable.night_day_switch)
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun checkPreferencesStatus() {
        launchWithLifecycle {
            viewModel.checkPreferencesStatus()
            observer()
        }
    }

    private suspend fun observer() {
        viewModel.state.collect {
            if (it.dayMode == ThemeModeEnum.DAY_MODE.name) nightDayMode() else dayNightMode()
        }
    }

    private fun checkDeviceStatus() {
        binding.dayNightSwitcher.getDrawable(
            baseContext,
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) R.drawable.night_day_switch else R.drawable.day_night_switch
        )
    }

    private fun dayNightHandling() {
        launchWithLifecycle() {
            viewModel.dayNightHandling()
            observer()
        }
    }

    private fun hideNavBar() {
        supportActionBar?.hide()
    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.topFragment.id, ChatFragment(), UserEnum.TOP_USER.name)
            replace(binding.bottomFragment.id, ChatFragment(), UserEnum.BOTTOM_USER.name)
                .commit()
        }
    }
}