package com.example.projectpreliminary.model.nowcast
import com.google.gson.annotations.SerializedName

data class Summary (
    @SerializedName("symbol_code" ) var symbolCode : String? = null
)