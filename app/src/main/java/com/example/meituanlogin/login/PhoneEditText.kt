package com.example.meituanlogin.login

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData

class PhoneEditText : AppCompatEditText {
    var phoneNumber = MutableLiveData("")
    var onNumberChangeListener: ((String) -> Unit)? = null

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        addTextChangedListener(
            onTextChanged = { mText, _, before, count ->
                if (before < count) {
                    if (mText?.length == 3 || mText?.length == 8) {
                        text?.append(" ")
                    }
                }
            },
            afterTextChanged = {
                it?.let {
                    val number = it.toString().replace(" ", "")
                    phoneNumber.postValue(number)
                    onNumberChangeListener?.let { callback ->
                        callback(number)
                    }
                }
            }
        )
    }

    fun clear(){
        //清空输入框的内容
        text?.clear()
        phoneNumber.postValue("")
        onNumberChangeListener?.let {
            it("")
        }
    }
}