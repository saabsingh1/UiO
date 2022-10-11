package com.example.projectpreliminary

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.projectpreliminary.data.Datapackage

class ShowWeatherViewPagerAdapter(fa: FragmentActivity, data : Datapackage,
                                  private val cityName: String
) :   FragmentStateAdapter(fa) {
    private val currentDay = data.currentDayDoublesArrayList
    private val restOfWeek = data.restOfTheWeekStringArrayList
    private val dayNames = data.dayNames
    private val measurementUnits = data.units
    private val symbolCodes = data.symbolCodes
    private val backgroundColorCodes = data.backgroundColorCodes
    private val textColorCodes = data.textColorCodes


    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> {
                return DailyReport.newInstance(
                    "${currentDay[0]}°\n",
                    currentDay[1].toString() + "",
                    "" + cityName,
                    currentDay[2].toString() + "",
                    symbolCodes[0][0] + "",
                    textColorCodes[0] + "",
                    backgroundColorCodes[0] + ""
                )
            }
            1 -> {

                return SevenDaysReport.newInstance(
                    dayNames[0] + "",
                    restOfWeek[0][0] + "°",
                    restOfWeek[0][1] + "°",
                    restOfWeek[0][2] + "",
                    restOfWeek[0][3] + "",
                    symbolCodes[1][0] + "",
                    symbolCodes[1][1] + "",
                    symbolCodes[1][2] + "",
                    symbolCodes[1][3] + "",
                    textColorCodes[1] + "",
                    backgroundColorCodes[1] + ""
                )
            }
            2 -> {

                return SevenDaysReport.newInstance(
                    dayNames[1] + "",
                    restOfWeek[1][0] + "°",
                    restOfWeek[1][1] + "°",
                    restOfWeek[1][2] + "",
                    restOfWeek[1][3] + "",
                    symbolCodes[2][0] + "",
                    symbolCodes[2][1] + "",
                    symbolCodes[2][2] + "",
                    symbolCodes[2][3] + "",
                    textColorCodes[2] + "",
                    backgroundColorCodes[2]
                )
            }
        }

        return DailyReport()
    }
}