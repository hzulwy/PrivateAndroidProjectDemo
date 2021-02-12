package com.example.myapplication

fun main() {
    val a:Int = 12
    var b =12
    //Int 不等于 Int?
    //kotlin都没有默认值,必须要初始化，java中成员变量是由默认值的
    println(a)

    var price = 23
    var str = "苹果的价格为${price}块钱"
    var str2 = """sasdsadsa
        |dfdssfds
        |dfsd
    """.trimIndent()
    var str1 = """
        sdasdasd
        ddadsad
    """.trimIndent()
    println(str2)
    var str3:String?=null//定义变量的时候不要使用?,而是使用lateinit
    a(str2)
}

fun a(str:String?){//可空类型
    //第1种解决方法
    if(str!=null){
        println("str的长度为${str.length}")
    }
    //第2种解决方法，若str为null直接打印null
    println("str的长度为${str?.length}")
    //第3种解决方法,如果str为null会直接包空指针异常
    println("str的长度为${str!!.length}")
   var str = fun1(str)
}

fun fun1(name:String):Unit{//相当于void
    println(name)
}