package com.example.smartfirstgdoconcepts

import android.util.Log

class WeatherStation(body: RawStation) {
    private var forecastUrl: String
    private var forecastHourlyUrl: String
    private var location: RelativeLocation

    init {
        forecastUrl = body.properties.forecast
        forecastHourlyUrl = body.properties.forecastHourly

        location = body.properties.relativeLocation.location
    }

    fun printLocation() {
        location.let { "%s, %s".format(it.city, it.state) }.let { Log.d("location", it) }
    }

    fun locationAsString(): String {
        return location.let { "%s, %s".format(it.city, it.state) }
    }
}