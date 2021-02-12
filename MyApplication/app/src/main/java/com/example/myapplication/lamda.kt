package com.example.myapplication

fun main() {
    //lamda
    var temp:(Int,Int) ->Int
    var sum = {x:Int,y:Int ->x+y}

    var temp1:(Int)->Unit
    temp1 = { print("${it}")}

}