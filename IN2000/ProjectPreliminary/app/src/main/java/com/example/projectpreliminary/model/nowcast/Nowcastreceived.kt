package com.example.projectpreliminary.model.nowcast

import com.google.gson.annotations.SerializedName


data class Nowcastreceived(
    @SerializedName("type"       ) var type       : String?     = null,
    @SerializedName("geometry"   ) var geometry   : Geometry?   = Geometry(),
    @SerializedName("properties" ) var properties : Properties? = Properties()
)