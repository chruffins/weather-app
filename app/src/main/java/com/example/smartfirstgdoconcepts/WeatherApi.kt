package com.example.smartfirstgdoconcepts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WeatherApi {
    @Headers("UserAgent: (android_test, chris.lee@chamberlain.com)")
    @GET("points/{lat},{lon}")
    fun getWeatherStation(@Path("lat") latitude: String, @Path("lon") longitude: String):
            Call<RawStation>

    @Headers("UserAgent: (android_test, chris.lee@chamberlain.com)")
    @GET("points/{gridX},{gridY}")
    fun getWeatherForecast(@Path("gridX") gridX: String, @Path("gridY") gridY: String):
            Call<String>

}