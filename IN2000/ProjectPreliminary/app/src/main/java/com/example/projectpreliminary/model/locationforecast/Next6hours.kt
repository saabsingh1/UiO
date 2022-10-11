package com.example.projectpreliminary.model.locationforecast

import com.google.gson.annotations.SerializedName

data class Next6hours(
    @SerializedName("summary" ) var summary : Hours6summarylocforec? = Hours6summarylocforec(),
    @SerializedName("details" ) var details : Hours6detailslocforec? = Hours6detailslocforec()
)
