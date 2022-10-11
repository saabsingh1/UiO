package com.example.projectpreliminary.model.nowcast
import com.google.gson.annotations.SerializedName

data class Timeseries (
    @SerializedName("time" ) var time : String? = null,
    @SerializedName("data" ) var data : Data?   = Data()
)
