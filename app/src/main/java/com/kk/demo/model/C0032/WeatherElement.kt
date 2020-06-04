package com.kk.demo.model.C0032

import com.google.gson.annotations.SerializedName

data class WeatherElement(
    @SerializedName("elementName")
    val elementName: String,
    @SerializedName("time")
    val time: MutableList<Time>
)