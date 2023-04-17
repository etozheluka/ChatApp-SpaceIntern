package com.example.chatapp_spaceintern.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.chatapp_spaceintern.R
import com.example.chatapp_spaceintern.databinding.ActivityMainBinding
import com.example.chatapp_spaceintern.presentation.ui.BottomFragment
import com.example.chatapp_spaceintern.presentation.ui.TopFragment
import com.example.chatapp_spaceintern.utils.extension.getDrawable
import com.example.chatapp_spaceintern.utils.extension.setColor
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

        binding.dayNightSwitcher.setOnClickListener {
            dayNightHandling()
        }
    }

    private fun dayNightMode() {
        binding.apply {
            layoutView.setColor(baseContext, R.color.darkTheme_dark_purple)
            dayNightSwitcher.getDrawable(baseContext,R.drawable.night_day_switch)
        }

    }

    private fun nightDayMode() {
        binding.apply {
            layoutView.setColor(baseContext, R.color.white)
            dayNightSwitcher.getDrawable(baseContext, R.drawable.day_night_switch)
        }
    }

    private fun checkPreferencesStatus() {
        lifecycleScope.launch {
            viewModel.checkPreferencesStatus({ nightDayMode() }, { dayNightMode() })
        }
    }

    private fun dayNightHandling() {
        lifecycleScope.launch {
            viewModel.dayNightHandling({ dayNightMode() }, { nightDayMode() })
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