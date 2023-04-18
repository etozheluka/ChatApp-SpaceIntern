package com.example.chatapp_spaceintern.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.chatapp_spaceintern.R
import com.example.chatapp_spaceintern.databinding.ActivityMainBinding
import com.example.chatapp_spaceintern.presentation.ui.BottomFragment
import com.example.chatapp_spaceintern.presentation.ui.TopFragment
import com.example.chatapp_spaceintern.utils.ThemeMode
import com.example.chatapp_spaceintern.utils.extension.getDrawable
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainActivityViewModel>()

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
        lifecycleScope.launch {
            viewModel.checkPreferencesStatus()
            observer()
        }
    }

    private suspend fun observer(){
        viewModel.state.collect {
            if (it.dayMode == ThemeMode.DAY_MODE.mode) {
                nightDayMode()
            } else {
                dayNightMode()
            }
        }
    }

    private fun checkDeviceStatus() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.dayNightSwitcher.getDrawable(baseContext, R.drawable.night_day_switch)
        } else {
            binding.dayNightSwitcher.getDrawable(baseContext, R.drawable.day_night_switch)
        }
    }

    private fun dayNightHandling() {
        lifecycleScope.launch {
            viewModel.dayNightHandling()
            observer()
        }
    }

    private fun hideNavBar() {
        supportActionBar?.hide()
    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.topFragment.id, TopFragment())
            replace(binding.bottomFragment.id, BottomFragment())
                .commit()
        }
    }
}