package com.example.projectpreliminary.model.locationforecast

import com.google.gson.annotations.SerializedName

data class Propertieslocforec(
    @SerializedName("meta"       ) var meta       : Metalocforec?                 = Metalocforec(),
    @SerializedName("timeseries" ) var timeseries : ArrayList<Timeserieslocforec> = arrayListOf()
)