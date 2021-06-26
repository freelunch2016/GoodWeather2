package com.goodweather.android.util

import android.widget.Toast
import com.goodweather.android.GoodWeatherApplication


fun String.toastShow(duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(GoodWeatherApplication.context,this,duration).show()
}

