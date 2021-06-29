package com.goodweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.goodweather.android.GoodWeatherApplication
import com.goodweather.android.logic.model.Place
import com.google.gson.Gson


object PlaceDao {

    private fun sharedPreferences() = GoodWeatherApplication.context.getSharedPreferences("good_weather", Context.MODE_PRIVATE)


    fun savePlace(place:Place){
        sharedPreferences().edit {
            putString("place",Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val string = sharedPreferences().getString("place", "")
        return Gson().fromJson(string,Place::class.java)
    }

     fun isPlaceSaved() = sharedPreferences().contains("place")
}