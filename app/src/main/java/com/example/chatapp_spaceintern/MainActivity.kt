package com.example.chatapp_spaceintern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.chatapp_spaceintern.databinding.ActivityMainBinding
import com.example.chatapp_spaceintern.presentation.bottom_screen.BottomFragment
import com.example.chatapp_spaceintern.presentation.top_screen.TopFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.topFragment, TopFragment()).commit()
        fm.beginTransaction().replace(R.id.bottomFragment, BottomFragment()).commit()
    }
}