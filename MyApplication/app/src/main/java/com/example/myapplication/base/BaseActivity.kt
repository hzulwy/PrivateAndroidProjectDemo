package com.example.myapplication.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {
      //这样写BaseActivity默认时final,不可以继承的
      //解决方法需要添加open或者abstract关键字在class前面

     abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

}