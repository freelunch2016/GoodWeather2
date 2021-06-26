package com.goodweather.android

import android.app.Application
import android.content.Context


class GoodWeatherApplication : Application() {

    companion object{
        lateinit var context: Context
        //彩云天气的token
        const val TOKEN = "pIe1zZfAVUuw0zpB"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}