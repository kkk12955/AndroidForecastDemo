package com.kk.demo.model.C0032

import com.google.gson.annotations.SerializedName

data class Parameter(
    @SerializedName("parameterName")
    val parameterName: String,
    @SerializedName("parameterUnit")
    val parameterUnit: String
)