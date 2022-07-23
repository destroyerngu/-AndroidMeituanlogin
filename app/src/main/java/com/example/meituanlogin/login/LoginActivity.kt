package com.example.meituanlogin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.meituanlogin.adapters.ClickEvents
import com.example.meituanlogin.databinding.ActivityLoginBinding
import com.example.meituanlogin.main.MainViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val model:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneText = binding.phoneEditText
        binding.clickEvents = ClickEvents()
        binding.model = model
        binding.lifecycleOwner = this
    }

}