package com.goodweather.android.logic.network

import com.goodweather.android.GoodWeatherApplication
import com.goodweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * 请求地址的service
 */
interface PlaceService {

    @GET("v2/place?token=${GoodWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query:String) :Call<PlaceResponse>

}