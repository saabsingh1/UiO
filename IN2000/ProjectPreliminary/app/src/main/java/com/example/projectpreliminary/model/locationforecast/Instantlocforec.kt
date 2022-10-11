package com.example.projectpreliminary.model.locationforecast
import com.google.gson.annotations.SerializedName

data class Instantlocforec(
    @SerializedName("details" ) var details : Detailslocforec? = Detailslocforec()
)
