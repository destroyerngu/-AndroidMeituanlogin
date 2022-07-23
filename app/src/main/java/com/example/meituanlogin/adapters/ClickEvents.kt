package com.example.meituanlogin.adapters

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.meituanlogin.PHONE_NUMBER_ORIGIN_KEY
import com.example.meituanlogin.PHONE_NUMBER_WITH_SPACE_KEY
import com.example.meituanlogin.R
import com.example.meituanlogin.login.LoginActivity
import com.example.meituanlogin.login.PhoneEditText
import com.example.meituanlogin.main.MainViewModel
import com.example.meituanlogin.verify.VerifyActivity

class ClickEvents {
    //手机号清空事件
    fun clearPhoneNumber(view:View,phoneEditText: PhoneEditText){
        phoneEditText.clear()
    }

    //更改显示和隐藏的图片
    fun changeEye(view: View, model: MainViewModel){
        val v = view as ImageView
        if (model.isPassword.value == true){
            v.setImageResource(R.drawable.eye)
            model.isPassword.postValue(false)
        }else{
            v.setImageResource(R.drawable.close_eye)
            model.isPassword.postValue(true)
        }
    }

    //checkBox的点击事件
    fun changeCheckStatus(view: View,model: MainViewModel){
        val v = view as CheckBox
        model.isChecked.postValue(v.isChecked)
    }

    //是否发送短信按钮的事件
    fun sendVerifyCode(
        view: View,
        model: MainViewModel,
        phoneEditText: PhoneEditText
    ){
        if (model.isChecked.value == false){
            //checkbox没有勾选 需要显示动画 提示用户
            model.showAnimation.postValue(true)
        }else{
            //跳转到下一个页面 phoneNumber
            val intent = Intent(view.context,VerifyActivity::class.java)
            //带空格的电话号码
            intent.putExtra(PHONE_NUMBER_WITH_SPACE_KEY,phoneEditText.text.toString())
            //原始手机号码
            intent.putExtra(PHONE_NUMBER_ORIGIN_KEY,phoneEditText.phoneNumber.value)
            //启动页面
            view.context.startActivity(intent)
        }
    }

    fun changeVerifyMethod(view: View, model: MainViewModel){
        val v = view as TextView
        if (model.isPhoneNumberVerify.value == true){
            model.isPhoneNumberVerify.postValue(false)
            v.text = "手机验证码登录"
        }else{
            model.isPhoneNumberVerify.postValue(true)
            v.text = "密码登录"
        }
    }

    //产出当前页面
    fun dismissView(view: View){
        val activity = view.context as LoginActivity
        activity.finish()
    }
}










