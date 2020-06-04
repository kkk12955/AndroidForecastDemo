package com.kk.demo.model.C0032

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("success")
    val success: String,
    @SerializedName("result")
    val result: JsonObject,
    @SerializedName("records")
    val records: Records
)