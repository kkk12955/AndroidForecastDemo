package com.kk.demo.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kk.demo.conf.Config
import com.kk.demo.model.C0032.Temperature
import com.kk.demo.model.C0032.Time
import com.kk.demo.retrofit.RetrofitManager
import com.kk.demo.retrofit.api.ApiC0032
import com.kk.demo.retrofit.api.Api_C0032
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAViewModel(
    private val context: Context
): ViewModel() {

    private var apiC0032: Api_C0032 = RetrofitManager.apiC0032()
    private var _temperatureList = MutableLiveData<Temperature>()
    val temperatureList: LiveData<Temperature>
        get() = _temperatureList

    init {
        //init LiveData
        _temperatureList.value = null
        getTemperatureList()
    }

    fun getTemperatureList() {
        //get temperature data use retrofit
        var mParams = ApiC0032(
            Config.getAuthorication(context)!!,
            "臺北市",
            "MinT"
        )
        apiC0032.request(mParams.Authorization, mParams.locationName, mParams.elementName).enqueue(object : Callback<Temperature> {
            override fun onResponse(call: Call<Temperature>, response: Response<Temperature>) {
                if (response.isSuccessful) {
                    if (response.body()!!.success.equals("true")) {
                        _temperatureList.postValue(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<Temperature>, t: Throwable) {
                //Handle failure
                Log.e("123","error")
            }
        })
    }
}