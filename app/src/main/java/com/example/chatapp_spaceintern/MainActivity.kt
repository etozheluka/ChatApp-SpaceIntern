package com.example.chatapp_spaceintern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.chatapp_spaceintern.databinding.ActivityMainBinding
import com.example.chatapp_spaceintern.presentation.main.FirstFragment
import com.example.chatapp_spaceintern.presentation.main.SecondFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.firstFragment, FirstFragment()).commit()
        fm.beginTransaction().replace(R.id.secondFragment, SecondFragment()).commit()
    }
}