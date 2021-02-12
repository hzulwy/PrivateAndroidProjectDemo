package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class FlowLayout(context: Context?, attrs: AttributeSet?): ViewGroup(context, attrs) {

    //一个类可以有主构造函数或者次构造函数其中一个就行

    constructor(context: Context) : this(context,attrs=null)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

}