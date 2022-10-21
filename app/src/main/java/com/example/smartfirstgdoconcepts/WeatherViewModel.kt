package com.example.smartfirstgdoconcepts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel() : ViewModel() {
    var currentWeatherStation: MutableLiveData<WeatherStation> = MutableLiveData()
    var currentForecast: MutableLiveData<String> = MutableLiveData()

    private var weatherRepository = WeatherRepository()

    init {
        currentWeatherStation = weatherRepository.currentWeatherStation
        currentForecast = weatherRepository.currentForecast
    }

    fun getStation(latitude: Double, longitude: Double) {
        weatherRepository.getWeatherStation(latitude, longitude)
    }
}