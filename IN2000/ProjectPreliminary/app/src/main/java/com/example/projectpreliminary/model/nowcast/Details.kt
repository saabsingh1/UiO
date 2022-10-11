package com.example.projectpreliminary.model.nowcast

import com.google.gson.annotations.SerializedName

data class Details (
    @SerializedName("air_temperature"     ) var airTemperature    : Double? = null,
    @SerializedName("precipitation_rate"  ) var precipitationRate : Double? = null, //According to json2kotlin this is Int? but lead to problems
    @SerializedName("relative_humidity"   ) var relativeHumidity  : Double? = null,
    @SerializedName("wind_from_direction" ) var windFromDirection : Double? = null,
    @SerializedName("wind_speed"          ) var windSpeed         : Double? = null,
    @SerializedName("wind_speed_of_gust"  ) var windSpeedOfGust   : Double? = null
)