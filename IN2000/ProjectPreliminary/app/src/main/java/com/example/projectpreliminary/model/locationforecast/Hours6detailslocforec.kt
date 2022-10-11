package com.example.projectpreliminary.model.locationforecast
import com.google.gson.annotations.SerializedName

data class Hours6detailslocforec(
    @SerializedName("air_temperature_max"          ) var airTemperatureMax          : Double? = null,
    @SerializedName("air_temperature_min"          ) var airTemperatureMin          : Double? = null,
    @SerializedName("precipitation_amount"         ) var precipitationAmount        : Double? = null,
    @SerializedName("precipitation_amount_max"     ) var precipitationAmountMax     : Double? = null,
    @SerializedName("precipitation_amount_min"     ) var precipitationAmountMin     : Double? = null,
    @SerializedName("probability_of_precipitation" ) var probabilityOfPrecipitation : Double? = null
)