package com.example.projectpreliminary.data

import com.google.gson.annotations.SerializedName

data class Params (

    @SerializedName("cap"    ) var cap    : String? = null,
    @SerializedName("period" ) var period : String? = null

)