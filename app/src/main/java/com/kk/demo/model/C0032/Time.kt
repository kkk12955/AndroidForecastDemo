package com.kk.demo.model.C0032

import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("parameter")
    val parameter: Parameter
)