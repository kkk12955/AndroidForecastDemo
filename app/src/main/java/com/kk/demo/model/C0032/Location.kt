package com.kk.demo.model.C0032

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("locationName")
    val locationName: String,
    @SerializedName("weatherElement")
    val weatherElement: MutableList<WeatherElement>
)