package com.example.projectpreliminary.model.locationforecast

import com.google.gson.annotations.SerializedName

data class Locforecreceived(

    @SerializedName("type"       ) var type       : String?     = null,
    @SerializedName("geometry"   ) var geometry   : Geometrylocforec?   = Geometrylocforec(),
    @SerializedName("properties" ) var properties : Propertieslocforec? = Propertieslocforec()

)
