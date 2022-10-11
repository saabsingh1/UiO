package com.example.projectpreliminary.model.locationforecast
import com.google.gson.annotations.SerializedName

data class Geometrylocforec(

    @SerializedName("type"        ) var type        : String?           = null,
    @SerializedName("coordinates" ) var coordinates : ArrayList<Double> = arrayListOf()

)
