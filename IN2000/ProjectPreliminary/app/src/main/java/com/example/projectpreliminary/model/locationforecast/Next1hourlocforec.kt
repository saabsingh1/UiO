package com.example.projectpreliminary.model.locationforecast
import com.google.gson.annotations.SerializedName

data class Next1hourlocforec(
    @SerializedName("summary" ) var summary : Hours1summarylocforec? = Hours1summarylocforec(),
    @SerializedName("details" ) var details : Hours1detailslocforec? = Hours1detailslocforec()
)
