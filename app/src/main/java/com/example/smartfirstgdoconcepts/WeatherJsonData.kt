package com.example.smartfirstgdoconcepts

import com.google.gson.annotations.SerializedName

data class RawStation(val properties: RawStationData)
data class RawStationData (

    @SerializedName("cwa"                 ) var cwa                 : String,
    @SerializedName("forecastOffice"      ) var forecastOffice      : String,
    @SerializedName("gridId"              ) var gridId              : String,
    @SerializedName("forecast"            ) var forecast            : String,
    @SerializedName("forecastHourly"      ) var forecastHourly      : String,
    @SerializedName("forecastGridData"    ) var forecastGridData    : String,
    @SerializedName("observationStations" ) var observationStations : String,
    @SerializedName("forecastZone"        ) var forecastZone        : String,
    @SerializedName("county"              ) var county              : String,
    @SerializedName("fireWeatherZone"     ) var fireWeatherZone     : String,
    @SerializedName("radarStation"        ) var radarStation        : String,

    @SerializedName("relativeLocation"    ) var relativeLocation : RawRelativeLocation
)

data class RawRelativeLocation(
    @SerializedName("properties")           var location: RelativeLocation
)
data class RelativeLocation(
    @SerializedName("city")       var city: String,
    @SerializedName("state")      var state: String
)