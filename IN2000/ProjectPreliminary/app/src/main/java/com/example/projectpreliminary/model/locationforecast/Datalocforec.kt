package com.example.projectpreliminary.model.locationforecast
import com.google.gson.annotations.SerializedName

data class Datalocforec(
    @SerializedName("instant"       ) var instant     : Instantlocforec? = Instantlocforec(),
    @SerializedName("next_12_hours" ) var next12Hours : Next12hours? = Next12hours(),
    @SerializedName("next_1_hours"  ) var next1Hours  : Next1hourlocforec? = Next1hourlocforec(),
    @SerializedName("next_6_hours"  ) var next6Hours  : Next6hours? = Next6hours()
)
