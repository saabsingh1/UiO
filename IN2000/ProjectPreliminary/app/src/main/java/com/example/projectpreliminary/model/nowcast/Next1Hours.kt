package com.example.projectpreliminary.model.nowcast
import com.google.gson.annotations.SerializedName

data class Next1Hours (
    @SerializedName("summary" ) var summary : Summary? = Summary(),
    @SerializedName("details" ) var details : PrecipitationAmount? = PrecipitationAmount()
)