package com.example.meituanlogin

import android.content.Context
import android.util.Log

const val PHONE_NUMBER_WITH_SPACE_KEY = "phone_number_width_space_key"
const val PHONE_NUMBER_ORIGIN_KEY = "phone_number_origin_key"

fun Context.dp2px(dp:Int):Float{
    return resources.displayMetrics.density*dp
}

fun logv(tag:String = "pxd", content:String){
    Log.v(tag,content)
}