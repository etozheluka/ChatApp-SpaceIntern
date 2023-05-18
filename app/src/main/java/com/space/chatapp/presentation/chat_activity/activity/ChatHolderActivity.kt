package com.space.chatapp.presentation.chat_activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.space.chatapp.R
import com.space.chatapp.databinding.ActivityMainBinding
import com.space.chatapp.presentation.chat_activity.viewmodel.ChatHolderViewModel
import com.space.chatapp.presentation.chat_screen.ui.fragment.ChatFragment
import com.space.chatapp.utils.ChatThemeMode
import com.space.chatapp.utils.extension.getDrawable
import com.space.chatapp.utils.extension.launchWithLifecycle
import com.space.chatapp.utils.extension.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatHolderActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModel<ChatHolderViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        hideNavBar()
        initFragment(3)
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
                    dayNightSwitcher?.getDrawable(binding.root.context, resourceId)
                    dayNightSwitcher?.setOnClickListener {
                        viewModel.themeUpdate()
                    }
                }
            }
        }
    }

    private fun hideNavBar() {
        supportActionBar?.hide()
    }

    /**
     * Initialize fragment container
     */
    private fun initFragment(containerCount: Int) {
        val fragmentList = mutableListOf<Fragment>()
        val fragmentTitleList = mutableListOf<String>()

        for (i in 0 until containerCount) {
            val fragment = createChatFragment(i)
            fragmentList.add(fragment)
            fragmentTitleList.add("Fragment ${i + 1}")
        }

        binding.viewPager?.adapter = ViewPagerFragmentAdapter(this@ChatHolderActivity, fragmentList)
    }

    private fun createChatFragment(index: Int): ChatFragment {
        val fragment by lazy { ChatFragment() }
        val bundle by lazy { createBundle(index) }
        fragment.arguments = bundle
        return fragment
    }

    /**
     * Create bundle for fragment
     */
    private fun createBundle(index: Int): Bundle {
        val bundle = Bundle()
        bundle.putString(USER_ID, "fragment_${index + 1}")
        return bundle
    }

    companion object {
        private const val CONTAINERS_STATE_KEY = "containers_state_key"
        private const val USER_ID = "userId"

    }
}