package com.example.chatapp_spaceintern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp_spaceintern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}