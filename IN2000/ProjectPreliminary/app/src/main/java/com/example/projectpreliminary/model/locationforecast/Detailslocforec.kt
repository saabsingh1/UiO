package com.example.projectpreliminary.model.locationforecast

import com.google.gson.annotations.SerializedName

data class Detailslocforec(
    @SerializedName("air_pressure_at_sea_level"     ) var airPressureAtSeaLevel      : Double? = null,
    @SerializedName("air_temperature"               ) var airTemperature             : Double? = null,
    @SerializedName("air_temperature_percentile_10" ) var airTemperaturePercentile10 : Double? = null,
    @SerializedName("air_temperature_percentile_90" ) var airTemperaturePercentile90 : Double? = null,
    @SerializedName("cloud_area_fraction"           ) var cloudAreaFraction          : Double? = null,
    @SerializedName("cloud_area_fraction_high"      ) var cloudAreaFractionHigh      : Double? = null,
    @SerializedName("cloud_area_fraction_low"       ) var cloudAreaFractionLow       : Double? = null,
    @SerializedName("cloud_area_fraction_medium"    ) var cloudAreaFractionMedium    : Double? = null,
    @SerializedName("dew_point_temperature"         ) var dewPointTemperature        : Double? = null,
    @SerializedName("fog_area_fraction"             ) var fogAreaFraction            : Double? = null,
    @SerializedName("relative_humidity"             ) var relativeHumidity           : Double? = null,
    @SerializedName("ultraviolet_index_clear_sky"   ) var ultravioletIndexClearSky   : Double? = null,
    @SerializedName("wind_from_direction"           ) var windFromDirection          : Double? = null,
    @SerializedName("wind_speed"                    ) var windSpeed                  : Double? = null,
    @SerializedName("wind_speed_of_gust"            ) var windSpeedOfGust            : Double? = null,
    @SerializedName("wind_speed_percentile_10"      ) var windSpeedPercentile10      : Double? = null,
    @SerializedName("wind_speed_percentile_90"      ) var windSpeedPercentile90      : Double? = null
)
