package com.example.meituanlogin.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.meituanlogin.User
import com.example.meituanlogin.databinding.ActivityMainBinding
import com.example.meituanlogin.login.LoginActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = model
        binding.lifecycleOwner = this

        //加载用户信息
        model.loadData(this)
        //model.updateState(this,false)
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}