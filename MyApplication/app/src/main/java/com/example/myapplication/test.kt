package com.example.myapplication

import java.util.ArrayList

class User(id: Int){//主构造函数
    var id:Int = 0
    get() {
        TODO()
    }
    set(value) {
        field = value
    }

    var name:String

//    init {
//        id = 0
//        name = ""
//    }

    init {//相当于Java的代码块，init块可以写多个，代码块会按照前后顺序执行
        this.id = id
        name =""
    }

    //一个类如果有次构造函数和主构造函数，那么次构造函数必须调用主构造函数
    constructor(id:Int,name: String) : this(id) {//次构造函数
        this.id = id
        this.name = name
    }
}

class User1{
    lateinit var name:String
    var age:Int = 0//8种基本类型无法使用lateinit属性
}

class User2(name: String){
   private var _name:String = name
       var name//name只是一个属性，数据保存与赋值是保存在幕后字段_name里面的
       get() = _name
        set(value) {
            _name = value
        }
}

class User3{
    lateinit var name:String
    var age :Int =0

    fun isNameInit():Boolean{//判断属性是否初始化
        return ::name.isInitialized
    }
}

fun <T> copy(dest: ArrayList<out T>, src: ArrayList<T>){//out拿出来的类型是T

}

data class User4(var name: String,var sex:String,var age:Int)

fun main() {
   var user =  User2("Brett")
    println(user.name)

    var user2= User3()
    if(user2.isNameInit()){
        println(user.name)
    }

    var user4 = User4("brett","男",18)

//    赋值方式1
//    var name:String = ""
//    var sex:String = ""
//    var age:Int = 0
//    name = user4.name
//    age = user4.age
//    sex = user4.age
//    赋值方式2
//    val (name,sex,age) = user4
//    其中一个不被赋值
    val (name,_,age) = user4

    val array = arrayListOf<Int>(2,4,3)
    val array1 = arrayListOf<Number>()

    copy(array,array1)

}