package com.example.projectpreliminary.model.locationforecast
import com.google.gson.annotations.SerializedName

data class Metalocforec(
    @SerializedName("updated_at" ) var updatedAt : String? = null,
    @SerializedName("units"      ) var units     : Unitslocforec?  = Unitslocforec()
)
