package com.goodweather.android.logic

import androidx.lifecycle.liveData
import com.goodweather.android.logic.dao.PlaceDao
import com.goodweather.android.logic.model.Place
import com.goodweather.android.logic.model.Weather
import com.goodweather.android.logic.network.GoodWeatherNetWork
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


/**
 * 仓库层
 *
 */
object Repository {

    fun searchPlaces(query: String) = liveData {

        val result = try {
            val placeResponse = GoodWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

        emit(result)

    }

    /**
     * 经纬度
     */
    fun refreshWeather(lng: String, lat: String) = liveData {

        val result = try {
            coroutineScope {
                val realtimeWeather = async {
                    GoodWeatherNetWork.getRealtimeWeather(lng, lat)
                }
                val dailyWeather = async {
                    GoodWeatherNetWork.getDailyWeather(lng, lat)
                }
                val realtime = realtimeWeather.await()
                val daily = dailyWeather.await()

                if (realtime.status == "ok" && daily.status == "ok") {
                    val weather = Weather(realtime.result.realtime, daily.result.daily)
                    Result.success(weather)
                } else {
                    Result.failure(RuntimeException(" realtime Response status is ${realtime.status},daily Response status is ${daily.status}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)

    }


    fun savePlace(place:Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavedPlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}








