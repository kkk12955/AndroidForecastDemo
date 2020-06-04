package com.kk.demo.retrofit

import com.google.gson.GsonBuilder
import com.kk.demo.conf.Config
import com.kk.demo.retrofit.api.Api_C0032
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager{

    lateinit var retrofit: Retrofit
    private val PAGE_TIMEOUT = 60L

    val apiC0032 = create(Api_C0032::class.java)

    fun <S> create(serviceClass: Class<S>): S {
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(PAGE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(PAGE_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(PAGE_TIMEOUT, TimeUnit.SECONDS).build()

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        // create com.kk.demo.retrofit
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Config.getEndPoint()) // Put your base URL
            .client(client)
            .build()

        return retrofit.create(serviceClass)
    }

    internal fun apiC0032():Api_C0032{
        return RetrofitManager.apiC0032
    }

    fun <T> callback(fn: (Throwable?, Response<T>?) -> Unit): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) = fn(null, response)
            override fun onFailure(call: Call<T>, t: Throwable) = fn(t, null)
        }
    }
}