package com.civitatis.pruebadesarrolloandroid.utlis

import android.util.Log

interface Logger{

    val nameClass: String

    fun logD(message: String){
        Log.d(nameClass,message)
    }

    fun logE(message: String){
        Log.e(nameClass,message)
    }
}