package com.example.meituanlogin.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meituanlogin.DataStoreUtil
import com.example.meituanlogin.User

class MainViewModel: ViewModel() {
    val user = MutableLiveData(User())
    var isPassword = MutableLiveData(true) //显示密码还是隐藏密码
    var isChecked = MutableLiveData(false) //记录checkBox的状态
    var showAnimation = MutableLiveData(false) //没有勾选协议时 是否需要动画
    var isPhoneNumberVerify = MutableLiveData(true)//手机号短信验证还是账号密码登录

    fun loadData(context: Context){
        val userInfo = DataStoreUtil.loadUser(context)
        //更新liveData的数据
        user.postValue(userInfo)
    }

    fun saveData(context: Context,aUser: User){
        DataStoreUtil.saveUser(context, aUser)
        //更新liveData的数据
        user.postValue(aUser)
    }

    fun updateState(context: Context, isLogin: Boolean){
        DataStoreUtil.updateLoginState(context, isLogin)
        //只更新isLogin一个属性 只触发监听这个属性的UI的刷新，其他属性的监听不受影响
        user.postValue(user.value!!.copy(isLogin = isLogin))
    }
}