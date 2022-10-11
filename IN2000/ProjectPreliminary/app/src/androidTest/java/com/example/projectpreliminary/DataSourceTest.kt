package com.example.projectpreliminary

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.projectpreliminary.data.Datasource
import com.example.projectpreliminary.model.locationforecast.Datalocforec
import com.google.gson.annotations.SerializedName
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat

@RunWith(AndroidJUnit4::class)
class DataSourceTest {
    private lateinit var appContext: Context
    private lateinit var datasource: Datasource
    private lateinit var time: Timeserieslocforec

    @Before
    fun setup() {
        appContext = ApplicationProvider.getApplicationContext()
        datasource = Datasource()
        time = Timeserieslocforec("2022-04-21T00:00:00Z")
    }

    @Test
    fun extractNameOfDayFromDateTest(timeseriesToGetDate : Timeserieslocforec) {
        println("TOGETDATE" + timeseriesToGetDate)
        val timeserieDateString = timeseriesToGetDate.time!!.split("T")[0]
        println("THIS IS TIMESERIEDATESTRING: " + timeserieDateString)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val timeserieDate = dateFormat.parse(timeserieDateString)
        println("THIS IS TIMESERIEDATE: " + timeserieDate)
        val svar = "2022-04-21"
        assertEquals(timeserieDate,svar)

    }
    data class Timeserieslocforec(
        @SerializedName("time" ) var time : String? = null,
        @SerializedName("data" ) var data : Datalocforec?   = Datalocforec()
    )
}