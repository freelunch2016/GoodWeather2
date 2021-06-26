package com.goodweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 定义位置的实体类
 */

data class PlaceResponse(val status:String,val places:List<Place>)

data class Place(val name:String,val location:Location,@SerializedName("formatted_address") val address:String)

//经纬度
data class Location(val lng:String,val lat:String)
