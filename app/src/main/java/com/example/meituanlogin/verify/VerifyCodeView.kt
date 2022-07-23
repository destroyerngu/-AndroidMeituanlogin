package com.example.meituanlogin.verify

import android.content.Context
import android.os.Build
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener
import com.example.meituanlogin.R
import com.example.meituanlogin.dp2px

class VerifyCodeView: ViewGroup {
    private val inputLists = arrayListOf<EditText>()
    private var count = 6  //验证码个数
    private val mSpace = context.dp2px(10).toInt() //间距
    private var mSize = 0   //控件的宽度
    private var mCurrentIndex = 0  //记录当前有焦点的控件索引
        /*set(value) {
            field = value
            inputLists.forEachIndexed { index, editText ->
                editText.isEnabled = index == value
            }
        }*/

    constructor(context: Context):super(context){initUI()}
    constructor(context: Context,attrs: AttributeSet):super(context,attrs){initUI()}

    private fun initUI(){
        //创建6个输入框
        for (i in 0 until count){
            val editText = EditText(context).apply {
                //设置光标颜色
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    setTextCursorDrawable(R.drawable.cursor_color_shape)
                }
                //设置键盘类型为手机号
                inputType = InputType.TYPE_CLASS_PHONE
                //限制输入一个字符
                filters = arrayOf(InputFilter.LengthFilter(1))
                //字体
                textSize = 40f
                isClickable = false
                //监听内容改变的事件
                addTextChangedListener(
                    afterTextChanged = {
                        it?.let {
                            if (text.length == 1){
                                //跳到下一个
                                nextFocus()
                            }
                            if (text.isEmpty()){
                                //跳到上一个
                                previousFocus()
                            }
                        }
                    }
                )
            }
            //添加到父容器
            addView(editText)
            //添加到数组
            inputLists.add(editText)
        }
    }

    private fun nextFocus(){
        mCurrentIndex++
        if (mCurrentIndex < count){
            inputLists[mCurrentIndex].requestFocus()
        }else{
            mCurrentIndex = count-1
        }
    }
    private fun previousFocus(){
        mCurrentIndex--
        if (mCurrentIndex >= 0){
            inputLists[mCurrentIndex].requestFocus()
        }else{
            mCurrentIndex = 0
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //计算每个控件的尺寸
        mSize = ((width - mSpace*(count-1))/count).toInt()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until count){
            //获取控件
            val v = getChildAt(i)
            val left = (mSpace+mSize)*i
            val right = left + mSize
            //告诉子控件给到他的显示区域
            v.layout(left,0,right,height)
            //设置左边的间距
            v.setPadding(mSize/10*2,0,0,0)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        postDelayed({
            //第一个EditText获取焦点
            inputLists.first().requestFocus()
            //弹出键盘
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(inputLists.first(),0)
        },200)

    }
}