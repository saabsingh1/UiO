package com.example.projectpreliminary.model.locationforecast

import com.google.gson.annotations.SerializedName

data class Hours12detailslocforec(
    @SerializedName("probability_of_precipitation" ) var probabilityOfPrecipitation : Double? = null
)