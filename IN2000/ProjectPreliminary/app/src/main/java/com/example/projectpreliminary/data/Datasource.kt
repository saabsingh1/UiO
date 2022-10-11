package com.example.projectpreliminary.data

import android.util.Log
import com.example.projectpreliminary.model.locationforecast.Locforecreceived
import com.example.projectpreliminary.model.locationforecast.Timeserieslocforec
import com.example.projectpreliminary.model.nowcast.Nowcastreceived
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.request.*
import java.text.SimpleDateFormat
import java.util.*


class Datasource() {

    val client = HttpClient()

    suspend fun loadData(coordinates : ArrayList<Float>) : Datapackage? {

        val lat = coordinates[0]
        val lon = coordinates[1]
        //val nowCastReceived : Nowcastreceived
        val locForecReceived : Locforecreceived

        //Create path
        //val nowCastPath = "https://in2000-apiproxy.ifi.uio.no/weatherapi/nowcast/2.0/complete?altitude=50&lat=$lat&lon=$lon"
        val locForecPath = "https://in2000-apiproxy.ifi.uio.no/weatherapi/locationforecast/2.0/complete?altitude=50&lat=$lat&lon=$lon"
        val textForecPath = "https://in2000-apiproxy.ifi.uio.no/weatherapi/textforecast/2.0/landoverview"

        // "https://in2000-apiproxy.ifi.uio.no/weatherapi/metalerts/1.1/available.xml?period=2019-10"


        val gson = Gson()

        try{
            //Retrieve from API
            //nowCastReceived = gson.fromJson(Fuel.get(nowCastPath).awaitString(), Nowcastreceived::class.java)
            locForecReceived = gson.fromJson(Fuel.get(locForecPath).awaitString(), Locforecreceived::class.java)
            //textForecReceived = gson.fromJson(Fuel.get())

            //printNowcast(nowCastReceived)
            //printLocForec(locForecReceived)

        } catch(exception : Exception){
            Log.d("ERROR IN DATASOURCE GETTING API PACKAGE ", exception.toString())
            return null
        }

        //This was for getting forecast text to add to content description
        //but shortage of time made it unfeasable
        getXMLdata()

        /*
        //Instantiate an empty Datapackage
        val dataPackToReturn = Datapackage(
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf()
        )

        //Adding measurement unit strings to the Datapackage
        val units = locForecReceived.properties?.meta?.units
        dataPackToReturn.units.add(units?.windSpeed.toString())
        dataPackToReturn.units.add(units?.precipitationAmount.toString())

        //Creating an array containing data from the first day
        extractDetailsFromCurrentDay(locForecReceived, dataPackToReturn)

        //Creating an array containing data from the second and third day
        extractDetailsFromDaysFollowing(locForecReceived, dataPackToReturn)

        //println("THIS IS ARRAY LIST OF SYMBOL CODES: " + dataPackToReturn.symbolCodes)
        */

        val ret = processData(locForecReceived)

        println("Symbolcodes: " + ret.symbolCodes)
        println("Bg Color: " + ret.backgroundColorCodes)
        println("Current day: " + ret.currentDayDoublesArrayList)
        println("Day names: " + ret.dayNames)
        println("Rest of week: " + ret.restOfTheWeekStringArrayList)
        println("Text colors: " + ret.textColorCodes)
        println("Units: " + ret.units)
        println(" ")
        //printLocForec(locForecReceived)

        return ret
    }

    //Processes data and extracts necessary information
    private fun processData(locForecReceived : Locforecreceived) : Datapackage{
        //Instantiate an empty Datapackage
        val dataPackToReturn = Datapackage(
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf()
        )

        //Adding measurement unit strings to the Datapackage
        val units = locForecReceived.properties?.meta?.units
        dataPackToReturn.units.add(units?.windSpeed.toString())
        dataPackToReturn.units.add(units?.precipitationAmount.toString())

        //Creating an array containing data from the first day
        extractDetailsFromCurrentDay(locForecReceived, dataPackToReturn)

        //Creating an array containing data from the second and third day
        extractDetailsFromDaysFollowing(locForecReceived, dataPackToReturn)

        return dataPackToReturn
    }

    //Extracts data from the day the app is used
    private fun extractDetailsFromCurrentDay(apiReceived : Locforecreceived, dataPackage : Datapackage) {
        val properties = apiReceived.properties
        val timeSeries = properties!!.timeseries
        val currentTimeSeries = timeSeries[0]
        val currentData = currentTimeSeries.data
        val currentDetails = currentData?.instant?.details

        //Adding Air temperature, Wind speed and Precipitation amount to Datapackage
        dataPackage.currentDayDoublesArrayList.add(currentDetails!!.airTemperature!!)
        dataPackage.currentDayDoublesArrayList.add(currentDetails!!.windSpeed!!)
        dataPackage.currentDayDoublesArrayList.add(currentData!!.next1Hours!!.details!!.precipitationAmount!!)

        //Assign non-null symbolcode for current day
        val currentDayArrayList = arrayListOf<String>()
        var symbolCode = currentData!!.next1Hours!!.summary!!.symbolCode!!
        if(symbolCode == "null"){
            symbolCode = currentData!!.next6Hours!!.summary!!.symbolCode!!
        }

        //Add background color and text color
        //appropriate for the current symbolcode to Datapackage
        val symbolCodeContainsUnfair = (symbolCode.contains("rain") or symbolCode.contains("thunder") or symbolCode.contains("cloudy"))

        if(symbolCode.contains("snow")){
            //println("THIS IS WHITE")
            dataPackage.backgroundColorCodes.add("#FFFFFFFF")
            dataPackage.textColorCodes.add("#FF000000")
        } else if(symbolCodeContainsUnfair) {
            //println("THIS IS DARK BLUE")
            dataPackage.backgroundColorCodes.add("#001F8B")
            dataPackage.textColorCodes.add("#FFB544")
        } else if(symbolCode.contains("fair") or symbolCode.contains("clearsky")){
            if(symbolCode.contains("day")){
                //println("THIS IS YELLOW")
                dataPackage.backgroundColorCodes.add("#FFB544")
                dataPackage.textColorCodes.add("#FF000000")
            } else {
                //println("THIS IS BLACK")
                dataPackage.backgroundColorCodes.add("#FF000000")
                dataPackage.textColorCodes.add("#FFB544")
            }
        }

        //Add symbolcode to Datapackage
        currentDayArrayList.add(symbolCode)
        dataPackage.symbolCodes.add(currentDayArrayList)
    }

    //Extract information from days following
    private fun extractDetailsFromDaysFollowing(apiReceived : Locforecreceived, dataPackage: Datapackage) {
        val timeSeries = apiReceived.properties?.timeseries
        val currentTimeserie = timeSeries?.get(0)
        val currentTime = currentTimeserie?.time.toString()

        val currentHour = currentTime.split("T")[1].take(2).toInt()
        val hoursInADay = 24
        val hoursUntilNextDay = hoursInADay - currentHour

        val numberOfDays = 2

        var daysIterations = 0
        var hourIterations = 0
        var lowestTemp = 0.0
        var highestTemp = 0.0
        var sumPrecipitation = 0.0
        var sumWindSpeed = 0.0

        //Going through each day specified in numberOfDays
        while (daysIterations <  numberOfDays) {

            val dayOffset = hoursUntilNextDay + (hoursInADay * daysIterations)

            val timeserieOfTheDay = timeSeries?.get(dayOffset)

            dataPackage.dayNames.add(extractNameOfDayFromDate(timeserieOfTheDay))

            //Iterate through every timeseries for every hour of the day
            while (hourIterations < hoursInADay) {

                //GET name of date
                val timeserie = timeSeries?.get(dayOffset + hourIterations)

                //Assign relevant data to variables for later modification
                val timeserieAirTemp = timeserie!!.data!!.instant!!.details!!.airTemperature!!
                val timeserieWindSpd = timeserie.data!!.instant!!.details!!.windSpeed!!
                var timeseriePrecAmo = timeserie.data!!.next1Hours!!.details!!.precipitationAmount
                if(timeseriePrecAmo == null)    timeseriePrecAmo = timeserie.data!!.next6Hours!!.details!!.precipitationAmount!!

                //When the first hour is processed
                if (hourIterations == 0) {
                    lowestTemp = timeserieAirTemp
                    highestTemp = timeserieAirTemp

                    dataPackage.symbolCodes.add(extractWeatherSymbolCodes(timeSeries, dayOffset))

                    //Assign relevant symbol code depending on what day it is
                    var relevantSymbolCode = ""

                    if(daysIterations == 0)
                        relevantSymbolCode = dataPackage.symbolCodes[1][2]

                    if(daysIterations == 1){
                        relevantSymbolCode = dataPackage.symbolCodes[2][2]
                    }

                    val symbolCodeContainsFair = (relevantSymbolCode.contains("fair") or relevantSymbolCode.contains("clearsky"))
                    val symbolCodeContainsUnfair = (relevantSymbolCode.contains("rain") or relevantSymbolCode.contains("cloudy"))

                    println("DAYS ITERATIONS: $daysIterations")
                    println("CONTAINS SNOW: $relevantSymbolCode.contains(\"snow\")")
                    println("CONTAINS UNFAIR: $symbolCodeContainsUnfair")
                    println("CONTAINS FAIR: $symbolCodeContainsFair")
                    println(" ")

                    if(relevantSymbolCode.contains("snow")){
                        //println("THIS IS WHITE")
                        dataPackage.backgroundColorCodes.add("#FFFFFFFF")
                        dataPackage.textColorCodes.add("#FF000000")
                    }
                    if(symbolCodeContainsUnfair) {
                        //println("THIS IS DARK BLUE")
                        dataPackage.backgroundColorCodes.add("#001F8B")
                        dataPackage.textColorCodes.add("#FFB544")
                    }
                    if(symbolCodeContainsFair){
                        if(relevantSymbolCode.contains("day")){
                            //println("THIS IS YELLOW")
                            dataPackage.backgroundColorCodes.add("#FFB544")
                            dataPackage.textColorCodes.add("#FF000000")
                        } else {
                            //println("THIS IS BLACK")
                            dataPackage.backgroundColorCodes.add("#FF000000")
                            dataPackage.textColorCodes.add("#FFB544")
                        }
                    }
                }

                if (timeserieAirTemp < lowestTemp) lowestTemp = timeserieAirTemp
                if (timeserieAirTemp > highestTemp) highestTemp = timeserieAirTemp
                sumWindSpeed += timeserieWindSpd
                sumPrecipitation += timeseriePrecAmo
                hourIterations += 1
            }

            val averageWindSpeed = String.format(Locale.ROOT, "%.2f", (sumWindSpeed / hoursInADay))
            val averagePrecipitation = String.format(Locale.ROOT, "%.2f", (sumPrecipitation / hoursInADay))

            //println("THIS IS AVERAGE WIND SPEED: " + averageWindSpeed)
            //println("THIS IS AVERAGE PRECIPITATION: " + averagePrecipitation)

            dataPackage.restOfTheWeekStringArrayList.add(arrayListOf())
            dataPackage.restOfTheWeekStringArrayList[daysIterations].add(lowestTemp.toString())
            dataPackage.restOfTheWeekStringArrayList[daysIterations].add(highestTemp.toString())
            dataPackage.restOfTheWeekStringArrayList[daysIterations].add(averageWindSpeed)
            dataPackage.restOfTheWeekStringArrayList[daysIterations].add(averagePrecipitation)

            hourIterations = 0
            daysIterations += 1
        }
    }

    //Extract name of the day for displaying
    fun extractNameOfDayFromDate(timeseriesToGetDate : Timeserieslocforec?) : String {
        //println("TOGETDATE" + timeseriesToGetDate)
        val timeserieDateString = timeseriesToGetDate!!.time!!.split("T")[0]
        //println("THIS IS TIMESERIEDATESTRING: " + timeserieDateString)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val timeserieDate = dateFormat.parse(timeserieDateString)
        //println("THIS IS TIMESERIEDATE: " + timeserieDate)
        return android.text.format.DateFormat.format("EEEE",timeserieDate).toString()
    }

    //For extracting weather symbol codes for morning, afternoon, evening and night
    private fun extractWeatherSymbolCodes(timeseries : ArrayList<Timeserieslocforec>, dayOffset : Int) : ArrayList<String>{
        val gettingSymbolCodesForTimes = arrayListOf(6, 12, 18, 24)
        val restOfWeekSymbolCodes = arrayListOf<String>()

        //GET SYMBOL CODE OF WEATHER TYPE FOR EACH SPECIFIED TIME OF DAY
        for(item in gettingSymbolCodesForTimes){
            val timeseriesForHour = timeseries.get((dayOffset + item))
            println("THIS IS HOUR FOR STUFF: " + (dayOffset + item))
            val timeForTimeserie = timeseriesForHour.time.toString().split("T")[1].take(2)

            var symbolCodeNullOrNot = timeseriesForHour.data?.next1Hours?.summary?.symbolCode.toString()

            if(symbolCodeNullOrNot == "null") {
                println("SYMBOLCODENULLORNOT IS NULL!!!")
                symbolCodeNullOrNot = timeseriesForHour.data?.next6Hours?.summary?.symbolCode.toString()
            } else {
                println("THIS IS TIME: " + timeForTimeserie + " AND SYMBOL CODE FOR TIME: " + symbolCodeNullOrNot)
                if(timeForTimeserie.contains("00")){
                    println("THIS IS FOR 00:00:00")
                    if(!symbolCodeNullOrNot.contains("night")){
                        //if(symbolCodeNullOrNot.contains("cloudy")){
                        //    println("THIS IS CLOUDY NIGHT")
                        //   symbolCodeNullOrNot += "_night"
                        //}

                        if(symbolCodeNullOrNot.contains("day")){
                            //println("THIS IS CHANGING FROM DAY TO NIGHT")
                            symbolCodeNullOrNot.replace("_day", "_night")
                        }
                    }
                } else {
                    println("THIS IS NOT FOR 00:00:00")
                    if(timeForTimeserie.contains("18")){
                        print("THIS IS FOR 18:00:00 " )
                        if(!symbolCodeNullOrNot.contains("night")){
                            print("THAT DOES NOT CONTAIN NIGHT SO... " )
                            if(symbolCodeNullOrNot.contains("cloudy")){
                                //println("THIS IS CLOUDY NIGHT")

                                //if(symbolCodeNullOrNot.contains("_day")){
                                //    println("THIS IS CHANGING FROM DAY TO NIGHT 1")
                                //    symbolCodeNullOrNot = symbolCodeNullOrNot.replace("_day", "_night")
                                //} else {
                                //    symbolCodeNullOrNot += "_night"
                                //}

                                //println("THIS IS NEW SYMBOLCODE: " + symbolCodeNullOrNot)
                            }

                            if(symbolCodeNullOrNot.contains("_day")){
                                println("THIS IS CHANGING FROM DAY TO NIGHT 2")
                                symbolCodeNullOrNot = symbolCodeNullOrNot.replace("_day", "_night")
                            }
                        }
                    } else {
                        if(symbolCodeNullOrNot.contains("night")){
                            println("THIS IS CHANGING FROM NIGHT TO DAY")
                            symbolCodeNullOrNot.replace("_night", "_day")
                        }
                    }
                }
            }

            //Debug prints
            //println("THIS IS TIME FOR TIMESERIE: " + timeForTimeserie)
            //println("THIS IS DAYOFFSET: " + dayOffset)
            //println("THIS IS HOURSUNTILNEXTDAY: " + hoursUntilNextDay)
            //println("THIS IS ITEM: " + item)
            //println("THIS IS GETTING WEATHER SYMBOL CODE FROM TIMESTAMP: " + ((dayOffset + hoursUntilNextDay) + item))
            //println("THIS IS WHAT IS RECEIVED 2: " + timeseries?.get(((dayOffset + hoursUntilNextDay) + item)).data?.next1Hours?.summary?.symbolCode.toString())
            //println("THIS IS SYMBOLCODENULLORNOT: " + symbolCodeNullOrNot)
            //println(" ")

            restOfWeekSymbolCodes.add(symbolCodeNullOrNot)
        }
        //println("THIS IS RESTOFWEEKSYMBOLCODES: " + restOfWeekSymbolCodes)
        return restOfWeekSymbolCodes
    }

    suspend fun getXMLdata() {

        val baseUrl = "https://in2000-apiproxy.ifi.uio.no/weatherapi/textforecast/2.0/landoverview"
        val r: String = client.get(baseUrl)
        val respons = xmlParser().parse(r.byteInputStream())
        //println("THIS IS RESPONSE!!! " + respons)
    }

    private fun printNowcast(dataResponse : Nowcastreceived){

        //Shortening the prints with variables
        val geoCoordinates = dataResponse.geometry!!.coordinates
        val metaUpdatedAt = dataResponse.properties?.meta?.updatedAt
        val metaRadarCoverage = dataResponse.properties?.meta?.radarCoverage

        //Metaunits
        val metaUnitAirTemp = dataResponse.properties?.meta?.units?.airTemperature
        val metaUnitPreciAmount = dataResponse.properties?.meta?.units?.precipitationAmount
        val metaUnitPreciRate = dataResponse.properties?.meta?.units?.precipitationRate
        val metaUnitRelHum = dataResponse.properties?.meta?.units?.relativeHumidity
        val metaUnitWindFromDir = dataResponse.properties?.meta?.units?.windFromDirection
        val metaUnitWindSpd = dataResponse.properties?.meta?.units?.windSpeed
        val metaUnitWindSpdOfGust = dataResponse.properties?.meta?.units?.windSpeedOfGust

        //ArrayList of Timeseries
        val timeseries = dataResponse.properties!!.timeseries

        //Printing out Response from /complete Nowcast
        //This is a printed representation of the entire dataset as it is retrieved


        println("LOGGING RESPONSE FOR DATARESPONSE")
        println("NOWCASTRECEIVED type: " + dataResponse.type.toString())
        println("GEOMETRY from NOWCASTRECEIVED")
        for((index, coordinate) in geoCoordinates.withIndex()){
            when(index){
                0 -> println("   " + "Longitude: " + coordinate)
                1 -> println("   " + "Latitude: " + coordinate)
                2 -> println("   " + "Altitude: " + coordinate + " meters above sea level")
            }
        }
        println(" ")

        //Properties contain metadata of
        //Radar coverage, update timestamp and measurement units for measurement details found below
        println("PROPERTIES from NOWCASTRECEIVED")
        println("   " + "META from PROPERTIES")
        println("      " + "Radar coverage: " + metaRadarCoverage.toString())
        println("      " + "Updated at: " + metaUpdatedAt.toString())
        println("   " + "UNITS of measurement from META")
        println("      " + "Unit for airTemperature: " + metaUnitAirTemp.toString())
        println("      " + "Unit for precipitationAmount: " + metaUnitPreciAmount.toString())
        println("      " + "Unit for precipitationRate: " + metaUnitPreciRate.toString())
        println("      " + "Unit for relativeHumidity: " + metaUnitRelHum.toString())
        println("      " + "Unit for windFromDirection: " + metaUnitWindFromDir.toString())
        println("      " + "Unit for windSpeed: " + metaUnitWindSpd.toString())
        println("      " + "Unit for windSpeedOfGust: " + metaUnitWindSpdOfGust.toString())
        println(" ")
        println("   " + "TIMESERIES from PROPERTIES")

        //Timeseries is an ArrayList with Timeseries objects
        //so we must loop through every object for printing details
        for((index, timeserie) in timeseries.withIndex()){

            val timeserieInstantDetails = timeserie.data?.instant?.details
            val timeserieNext1Hours = timeserie.data?.next1Hours

            //For some reason, only the first timeseries object has complete details
            if(index == 0){

                println("      " + "TIMESERIE nr " + index)
                println("         " + "TIMESERIE time: " + timeserie.time)
                println("         " + "DATA from TIMESERIE")
                println("            " + "INSTANT from DATA")

                //Numeric measurement details for a series of time
                println("               " + "DETAILS from INSTANT")
                println("                  " + "airTemperature: " + timeserieInstantDetails?.airTemperature.toString() + " " + metaUnitAirTemp.toString())
                println("                  " + "precipitationRate: " + timeserieInstantDetails?.precipitationRate.toString() + " " + metaUnitPreciRate.toString())
                println("                  " + "relativeHumidity: " + timeserieInstantDetails?.relativeHumidity.toString() + " " + metaUnitRelHum.toString())
                println("                  " + "windFromDirection: " + timeserieInstantDetails?.windFromDirection.toString() + " " + metaUnitWindFromDir.toString())
                println("                  " + "windSpeed: " + timeserieInstantDetails?.windSpeed.toString() + " " + metaUnitWindSpd.toString())
                println("                  " + "windSpeedOfGust: " + timeserieInstantDetails?.windSpeedOfGust.toString() + " " + metaUnitWindSpdOfGust.toString())
                println(" ")
                println("            " + "NEXT1HOUR from DATA")
                println("               " + "PRECIPITATIONAMOUNT from NEXT1HOUR")
                println("                  " + "precipitationAmount: " + timeserieNext1Hours?.details?.precipitationAmount + " " + metaUnitPreciAmount.toString())
                println("               " + "SUMMARY from NEXT1HOUR")
                println("                  " + "symbolCode: " + timeserieNext1Hours?.summary?.symbolCode)
                println(" ")
            }

            //So for all timeseries objects except the first, only "precipitation rate" isn't Null
            //Which is why we only print timestamp for the Timeseries object and "precipitation rate"
            else {
                println("      " + "TIMESERIE nr " + index)
                println("         " + "TIMESERIE time: " + timeserie.time)
                println("         " + "DATA from TIMESERIE")
                println("            " + "INSTANT from DATA")
                println("               " + "DETAILS from INSTANT")
                println("                  " + "precipitationRate: " + timeserieInstantDetails?.precipitationRate.toString() + " " + metaUnitPreciRate.toString())
                println(" ")
            }
        }
    }
    private fun printLocForec(locForecReceived : Locforecreceived){
        val type = locForecReceived.type
        val geometry = locForecReceived.geometry
        val coordinates = geometry?.coordinates
        val properties = locForecReceived.properties

        val meta = properties?.meta
        val updatedAt = meta?.updatedAt
        val units = meta?.units

        val timeseries = properties?.timeseries

        println("LOGGING RESPONSE FOR LOCATION FORECAST DATARESPONSE")
        println("LOCATION FORECAST type: " + type.toString())
        println("GEOMETRY from LOCATION FORECAST")
        for((index, coordinate) in coordinates!!.withIndex()){
            when(index){
                0 -> println("   " + "Longitude: " + coordinate)
                1 -> println("   " + "Latitude: " + coordinate)
                2 -> println("   " + "Altitude: " + coordinate + " meters above sea level")
            }
        }
        println(" ")

        //Properties contain metadata of
        //Update timestamp and measurement units for measurement details found below
        println("PROPERTIES from LOCATION FORECAST")
        println("   " + "META from PROPERTIES")
        println("      " + "Updated at: " + updatedAt.toString())
        println("   " + "UNITS of measurement from META")
        println("      " + "Unit for airPressureAtSeaLevel: " + units?.airPressureAtSeaLevel)
        println("      " + "Unit for airTemperature: " + units?.airTemperature)
        println("      " + "Unit for airTemperatureMax: " + units?.airTemperatureMax)
        println("      " + "Unit for airTemperatureMin: " + units?.airTemperatureMin)
        println("      " + "Unit for cloudAreaFractionHigh: " + units?.cloudAreaFractionHigh)
        println("      " + "Unit for cloudAreaFractionMedium: " + units?.cloudAreaFractionMedium)
        println("      " + "Unit for cloudAreaFractionLow: " + units?.cloudAreaFractionLow)
        println("      " + "Unit for cloudAreaFraction: " + units?.cloudAreaFraction)
        println("      " + "Unit for dewPointTemperature: " + units?.dewPointTemperature)
        println("      " + "Unit for fogAreaFraction: " + units?.fogAreaFraction)
        println("      " + "Unit for precipitationAmount: " + units?.precipitationAmount)
        println("      " + "Unit for precipitationAmountMax: " + units?.precipitationAmountMax)
        println("      " + "Unit for precipitationAmountMin: " + units?.precipitationAmountMin)
        println("      " + "Unit for probabilityOfPrecipitation: " + units?.probabilityOfPrecipitation)
        println("      " + "Unit for probabilityOfThunder: " + units?.probabilityOfThunder)
        println("      " + "Unit for relativeHumidity: " + units?.relativeHumidity)
        println("      " + "Unit for ultravioletIndexClearSky: " + units?.ultravioletIndexClearSky)
        println("      " + "Unit for windFromDirection: " + units?.windFromDirection)
        println("      " + "Unit for windSpeed: " + units?.windSpeed)
        println("      " + "Unit for windSpeedOfGust: " + units?.windSpeedOfGust)
        println(" ")
        println("   " + "TIMESERIES from PROPERTIES")

        //Timeseries is an ArrayList with Timeseries objects
        //so we must loop through every object for printing details
        for((index, timeserie) in timeseries!!.withIndex()){
            val data = timeserie.data
            val details = data?.instant?.details
            val next12hours = data?.next12Hours
            val next1hours = data?.next1Hours
            val next6hours = data?.next6Hours

            println("      " + "TIMESERIE nr " + index)
            println("         " + "TIMESERIE time: " + timeserie.time)
            println("         " + "DATA from TIMESERIE")
            println("            " + "INSTANT from DATA")

            //Numeric measurement details for a series of time
            println("               " + "DETAILS from INSTANT")
            println("                  " + "airPressureAtSeaLevel: " + details?.airPressureAtSeaLevel)
            println("                  " + "airTemperature: " + details?.airTemperature)
            println("                  " + "airTemperaturePercentile10: " + details?.airTemperaturePercentile10)
            println("                  " + "airTemperaturePercentile90: " + details?.airTemperaturePercentile90)
            println("                  " + "cloudAreaFractionHigh: " + details?.cloudAreaFractionHigh)
            println("                  " + "cloudAreaFractionMedium: " + details?.cloudAreaFractionMedium)
            println("                  " + "cloudAreaFractionLow: " + details?.cloudAreaFractionLow)
            println("                  " + "cloudAreaFraction: " + details?.cloudAreaFraction)
            println("                  " + "dewPointTemperature: " + details?.dewPointTemperature)
            println("                  " + "fogAreaFraction: " + details?.fogAreaFraction)
            println("                  " + "relativeHumidity: " + details?.relativeHumidity)
            println("                  " + "ultravioletIndexClearSky: " + details?.ultravioletIndexClearSky)
            println("                  " + "windFromDirection: " + details?.windFromDirection)
            println("                  " + "windSpeed: " + details?.windSpeed)
            println("                  " + "windSpeedOfGust: " + details?.windSpeedOfGust)
            println(" ")
            println("            " + "NEXT 12 HOURS from DATA")
            println("               " + "SUMMARY")
            println("                  " + "Symbol code: " + next12hours?.summary?.symbolCode)
            println("                  " + "Symbol code: " + next12hours?.summary?.symbolConfidence)
            println("               " + "DETAILS")
            println("                  " + "probabilityOfPrecipitation: " + next12hours?.details?.probabilityOfPrecipitation)
            println(" ")
            println("            " + "NEXT 6 HOURS from DATA")
            println("               " + "SUMMARY")
            println("                  " + "Symbol code: " + next6hours?.summary?.symbolCode)
            println("               " + "DETAILS")
            println("                  " + "airTemperatureMax: " + next6hours?.details?.airTemperatureMax)
            println("                  " + "airTemperatureMin: " + next6hours?.details?.airTemperatureMin)
            println("                  " + "precipitationAmount: " + next6hours?.details?.precipitationAmount)
            println("                  " + "precipitationAmountMax: " + next6hours?.details?.precipitationAmountMax)
            println("                  " + "precipitationAmountMin: " + next6hours?.details?.precipitationAmountMin)
            println("                  " + "probabilityOfPrecipitation: " + next6hours?.details?.probabilityOfPrecipitation)
            println(" ")
            println("            " + "NEXT 1 HOURS from DATA")
            println("               " + "SUMMARY")
            println("                  " + "Symbol code: " + next1hours?.summary?.symbolCode)
            println("               " + "DETAILS")
            println("                  " + "precipitationAmount: " + next1hours?.details?.precipitationAmount)
            println("                  " + "precipitationAmountMax: " + next1hours?.details?.precipitationAmountMax)
            println("                  " + "precipitationAmountMin: " + next1hours?.details?.precipitationAmountMin)
            println("                  " + "probabilityOfPrecipitation: " + next1hours?.details?.probabilityOfPrecipitation)
            println("                  " + "probabilityOfThunder: " + next1hours?.details?.probabilityOfThunder)
            println(" ")

        }
    }
    


}