package com.example.meituanlogin.adapters

import android.animation.ObjectAnimator
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.meituanlogin.dp2px
import com.example.meituanlogin.main.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("setVisibility")
fun FloatingActionButton.setVisibility(state: Boolean?){
    visibility = if (state == null){
        View.VISIBLE
    }else {
        if (state) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("showText")
fun TextView.showText(state: Boolean?){
    text = if (state == null){
        "请点击登录"
    }else {
        if (state) {
            "已登录"
        } else {
            "请点击登录"
        }
    }
}

@BindingAdapter("setEnabled")
fun Button.setEnabled(number: String) {
    isEnabled = number.length == 11

}

@BindingAdapter("setVisibility")
fun ImageView.setVisibility(number: String) {
    visibility = if (number.isNotEmpty()) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

@BindingAdapter("shouldShowPassword")
fun EditText.shouldShowPassword(state:Boolean){
    //密码的显示和隐藏
    if (state){
        transformationMethod = PasswordTransformationMethod.getInstance()
    }else{
        transformationMethod = HideReturnsTransformationMethod.getInstance()
    }
    //将光标定位到最后
    setSelection(text.length)
}

@BindingAdapter("shouldShowAnimation")
fun TextView.shouldShowAnimation(shouldShow: Boolean){
    //确定偏移量
    val offsetX = context.dp2px(10)
    if (shouldShow){
        //创建左右摆动的动画效果 改变属性的x坐标 translationX
        val animator = ObjectAnimator.ofFloat(
            this,
            "translationX",
            0f,-offsetX,offsetX*2,0f
        )
        animator.duration = 200 //完成动画的时间
        animator.repeatCount = 2 //这个动画效果执行2次
        animator.interpolator = LinearInterpolator() //匀速
        animator.start() //开启动画
    }
}

@BindingAdapter("shouldShow")
fun View.shouldShow(isPhoneNumberVerify: Boolean){
    visibility = if (isPhoneNumberVerify){
        View.GONE
    }else{
        View.VISIBLE
    }
}

@BindingAdapter("showTitle")
fun Button.showTitle(isPhoneNumberVerify: Boolean){
    text = if (isPhoneNumberVerify){
        "发送手机短信验证码"
    }else{
        "登录"
    }
}


