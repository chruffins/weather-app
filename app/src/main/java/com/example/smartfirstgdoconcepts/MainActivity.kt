package com.example.smartfirstgdoconcepts

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.app.ActivityCompat
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var currentLocation: Location
    lateinit var locationManager: LocationManager
    lateinit var locationListener: LocationListener

    private lateinit var textWeatherLocation: TextView

    var weatherViewModel: WeatherViewModel = WeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textWeatherLocation = findViewById(R.id.textWeatherLocation)

        getLocationUpdates()
        weatherViewModel.currentWeatherStation.observeForever {
            it.printLocation()
            textWeatherLocation.text = it.locationAsString()
        }
    }


    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
            false
        } else {
            true
        }
    }

    private fun getLocationUpdates() {
        @SuppressLint("MissingPermission")
        if (isLocationPermissionGranted()) {
            Log.d("info", "wowie!!! nice!!!")
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

            val basicCriteria = Criteria()
            basicCriteria.accuracy = Criteria.ACCURACY_FINE

            val bestProvider: String? = locationManager.getBestProvider(basicCriteria, true)
            locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    currentLocation = location
                    Log.d("info", "location: %f, %f".format(currentLocation.latitude, currentLocation.longitude))
                    weatherViewModel.getStation(currentLocation.latitude, currentLocation.longitude)
                }

                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

            if (bestProvider != null) {
                Log.d("info", bestProvider)
                locationManager.requestLocationUpdates(bestProvider, 5000, 1000f, locationListener)
            } else {
                Log.d("info", "There is no best provider. FUck.")
            }
        }
    }
}