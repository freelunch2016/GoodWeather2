package com.goodweather.android.logic

import androidx.lifecycle.liveData
import com.goodweather.android.logic.model.Place
import com.goodweather.android.logic.model.PlaceResponse
import com.goodweather.android.logic.network.GoodWeatherNetWork


/**
 * 仓库层
 *
 */
object Repository {

    fun searchPlaces(query:String)  = liveData{

        val result = try {
            val placeResponse = GoodWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }

        emit(result)

    }
}








