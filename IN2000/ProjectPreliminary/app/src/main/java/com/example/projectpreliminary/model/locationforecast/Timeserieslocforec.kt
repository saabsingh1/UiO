package com.example.projectpreliminary.model.locationforecast

import com.google.gson.annotations.SerializedName

data class Timeserieslocforec(
    @SerializedName("time" ) var time : String? = null,
    @SerializedName("data" ) var data : Datalocforec?   = Datalocforec()
)
