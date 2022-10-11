package com.example.projectpreliminary.model.nowcast
import com.google.gson.annotations.SerializedName

data class PrecipitationAmount(
    @SerializedName("precipitation_amount" ) var precipitationAmount : Double? = null
)