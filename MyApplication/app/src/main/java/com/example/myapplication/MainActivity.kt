package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.base.BaseActivity

class MainActivity : BaseActivity() {

    var mTextView: TextView? = null
    lateinit var mt:TextView//声明为延迟加载,这样就不需要在定义时赋值
    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        TODO("Not yet implemented")
        mTextView = findViewById(R.id.tv)
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextView?.setText("")//若为null则不会执行setText方法，否则执行settext方法
        mTextView!!.setText("")//自己确定mTextView不为null.这两种写法都不太好
    }

}