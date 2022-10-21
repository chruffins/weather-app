package com.example.smartfirstgdoconcepts

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val STATION_API = "https://api.weather.gov/"

    fun getData(): Retrofit =
        Retrofit.Builder()
            .baseUrl(STATION_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}