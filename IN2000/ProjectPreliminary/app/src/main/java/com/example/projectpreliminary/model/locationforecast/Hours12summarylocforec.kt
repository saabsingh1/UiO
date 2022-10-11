package com.example.projectpreliminary.model.locationforecast
import com.google.gson.annotations.SerializedName

data class Hours12summarylocforec(
    @SerializedName("symbol_code"       ) var symbolCode       : String? = null,
    @SerializedName("symbol_confidence" ) var symbolConfidence : String? = null
)