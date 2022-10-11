package com.example.projectpreliminary.model.nowcast
import com.google.gson.annotations.SerializedName

data class Properties (
    @SerializedName("meta"       ) var meta       : Meta?                 = Meta(),
    @SerializedName("timeseries" ) var timeseries : ArrayList<Timeseries> = arrayListOf()
)