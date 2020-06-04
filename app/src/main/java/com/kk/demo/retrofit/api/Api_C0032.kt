package com.kk.demo.retrofit.api

import com.kk.demo.model.C0032.Temperature
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api_C0032 {
    @GET("rest/datastore/F-C0032-001")
    fun request(
        @Query("Authorization") Authorization: String,
        @Query("locationName") locationName: String,
        @Query("elementName") elementName: String)
    : Call<Temperature>
}

class ApiC0032 internal constructor(
    internal val Authorization: String,
    internal val locationName: String,
    internal val elementName: String
)