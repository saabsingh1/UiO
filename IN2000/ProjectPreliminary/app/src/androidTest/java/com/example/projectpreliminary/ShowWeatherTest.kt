package com.example.projectpreliminary

import android.content.Context
import android.location.Geocoder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.junit.jupiter.api.Test

@RunWith(AndroidJUnit4::class)
class ShowWeatherTest {
    lateinit var appContext: Context
    lateinit var geocoder: Geocoder
    lateinit var weather: ShowWeather

    @Before()
    fun setup() {
        appContext = ApplicationProvider.getApplicationContext()
        geocoder = Geocoder(appContext)
        weather = ShowWeather()
    }

    /*
    @Test
    fun getCityName() {
        val city = weather.getCityName(geocoder, 60.3913, 5.3221).toString().lowercase()
        assertEquals("bergen", city)
    }

    @Test
    fun getCoordinates() {
        val coordinates = weather.getCoordinates("bergen")
        assertEquals(coordinates.get(0), 60.3913)
    }

     */
}