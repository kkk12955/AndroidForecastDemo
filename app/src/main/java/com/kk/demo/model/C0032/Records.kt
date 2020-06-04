package com.kk.demo.model.C0032

import com.google.gson.annotations.SerializedName

data class Records(
    @SerializedName("datasetDescription")
    val datasetDescription: String,
    @SerializedName("location")
    val location: MutableList<Location>
)