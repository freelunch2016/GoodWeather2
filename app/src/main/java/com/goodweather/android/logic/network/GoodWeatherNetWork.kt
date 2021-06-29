package com.goodweather.android.logic.network

import android.app.DownloadManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * 上层 统一的网络访问入口
 */
object GoodWeatherNetWork {

    private suspend fun <T> Call<T>.await():T{

        return  suspendCoroutine {continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null){
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException( RuntimeException(" response is null !"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })

        }
    }

    private  val placeService = ServiceCreator.create<PlaceService>()
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    /**
     * 请求天气信息
     */
    private val weatherService = ServiceCreator.create(WeatherService::class.java)
    suspend fun getRealtimeWeather(lng:String,lat:String) = weatherService.getRealtimeWeather(lng, lat).await()
    suspend fun getDailyWeather(lng:String,lat:String) = weatherService.getDailyWeather(lng, lat).await()

}