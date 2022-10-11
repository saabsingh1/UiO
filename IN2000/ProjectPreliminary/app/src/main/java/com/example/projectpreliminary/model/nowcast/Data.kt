package com.example.projectpreliminary.model.nowcast

import com.google.gson.annotations.SerializedName


data class Data (

    @SerializedName("instant"      ) var instant    : Instant?    = Instant(),
    @SerializedName("next_1_hours" ) var next1Hours : Next1Hours? = Next1Hours()

)