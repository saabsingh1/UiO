package com.example.projectpreliminary.model.locationforecast

import com.google.gson.annotations.SerializedName

data class Next12hours(
    @SerializedName("summary" ) var summary : Hours12summarylocforec? = Hours12summarylocforec(),
    @SerializedName("details" ) var details : Hours12detailslocforec? = Hours12detailslocforec()
)
