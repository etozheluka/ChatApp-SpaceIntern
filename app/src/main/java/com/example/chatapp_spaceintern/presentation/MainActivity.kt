package com.example.chatapp_spaceintern.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.chatapp_spaceintern.R
import com.example.chatapp_spaceintern.databinding.ActivityMainBinding
import com.example.chatapp_spaceintern.presentation.ui.BottomFragment
import com.example.chatapp_spaceintern.presentation.ui.TopFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        lifecycleScope.launch {
            val mode = viewModel.getBoolean()
            if (mode.getOrNull() == true) {
                nightDayMode()
            } else {
                dayNightMode()
            }
        }

        binding.dayNightSwitcher.setOnClickListener {
            lifecycleScope.launch {
                val mode = viewModel.getBoolean()
                if (mode.getOrNull() == true) {
                    dayNightMode()
                    viewModel.saveBoolean(false)

                } else {
                    nightDayMode()
                    viewModel.saveBoolean(true)
                }
            }
        }

        supportFragmentManager.beginTransaction().apply {
            replace(binding.topFragment.id, TopFragment())
            replace(binding.bottomFragment.id, BottomFragment())
                .commit()
        }
    }

    private fun dayNightMode() {
        binding.layoutView.setBackgroundColor(
            ContextCompat.getColor(
                baseContext,
                R.color.darkTheme_dark_purple
            )
        )
        binding.dayNightSwitcher.background =
            ContextCompat.getDrawable(baseContext, R.drawable.night_day_switch)
    }

    private fun nightDayMode() {
        binding.layoutView.setBackgroundColor(ContextCompat.getColor(baseContext, R.color.white))
        binding.dayNightSwitcher.background =
            ContextCompat.getDrawable(baseContext, R.drawable.day_night_switch)
    }
}