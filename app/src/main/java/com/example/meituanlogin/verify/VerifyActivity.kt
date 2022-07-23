package com.example.meituanlogin.verify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.meituanlogin.PHONE_NUMBER_ORIGIN_KEY
import com.example.meituanlogin.PHONE_NUMBER_WITH_SPACE_KEY
import com.example.meituanlogin.R
import com.example.meituanlogin.databinding.ActivityVerifyBinding
import com.example.meituanlogin.logv

class VerifyActivity : AppCompatActivity() {
    lateinit var binding:ActivityVerifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //获取传递过来的数据
        val spacePhoneNumber = intent.getStringExtra(PHONE_NUMBER_WITH_SPACE_KEY)
        val originPhoneNumber = intent.getStringExtra(PHONE_NUMBER_ORIGIN_KEY)

    }
}