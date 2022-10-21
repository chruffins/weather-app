package com.example.smartfirstgdoconcepts

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository() {
    private val retrofitClient = RetrofitClient.getData()
    private val weatherApi = retrofitClient.create(WeatherApi::class.java)

    var currentWeatherStation: MutableLiveData<WeatherStation> = MutableLiveData()
    var currentForecast: MutableLiveData<String> = MutableLiveData()

    fun getWeatherStation(latitude: Double, longitude: Double) {
        Log.d("info", "making a call to get weather station")
        weatherApi.getWeatherStation("%.4f".format(latitude), "%.4f".format(longitude)).enqueue(object: Callback<RawStation> {
            override fun onResponse(call: Call<RawStation>, response: Response<RawStation>) {
                Log.d("info", "got a response.")
                if (response.body() != null) {
                    currentWeatherStation.value = WeatherStation(response.body()!!)
                    Log.d("info", "ladies and gentlemen...")
                }
            }

            override fun onFailure(call: Call<RawStation>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getWeatherForecast(gridX: String, gridY: String) {
        weatherApi.getWeatherForecast(gridX, gridY).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.body() != null) {
                    currentForecast.value = response.body()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
