package com.example.projectpreliminary.data

import android.util.Xml
import com.example.projectpreliminary.model.textforecast.ForecastLocation
import com.example.projectpreliminary.model.textforecast.ForecastTime
import com.example.projectpreliminary.model.textforecast.ForecastType
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private val ns : String? = null

class xmlParser {

    fun parse(inputStream: InputStream): MutableList<ForecastTime> {
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(it, null)
            parser.nextTag()
            return readTextforecast(parser)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readTextforecast(parser: XmlPullParser): MutableList<ForecastTime> {
        val times = mutableListOf<ForecastTime>()

        parser.require(XmlPullParser.START_TAG, ns, "textforecast")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }

            if(parser.name == "time"){
                times.add(readTime(parser))
            } else {
                skip(parser)
            }
        }
        return times
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readTime(parser: XmlPullParser): ForecastTime {
        parser.require(XmlPullParser.START_TAG, ns, "time")
        var attributeIndex = 0
        var from : String? = null
        var to : String? = null

        while(attributeIndex < parser.attributeCount){

            when(parser.getAttributeName(attributeIndex)){
                "from" ->   from = parser.getAttributeValue(attributeIndex)
                "to" ->     to = parser.getAttributeValue(attributeIndex)
            }
            attributeIndex ++
        }

        var forecastType : ForecastType? = null

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }

            if(parser.name == "forecasttype") forecastType = readForecastType(parser)

        }

        return ForecastTime(from, to, forecastType)
    }

    //
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readForecastType(parser: XmlPullParser): ForecastType {
        parser.require(XmlPullParser.START_TAG, ns, "forecasttype")
        var name = ""
        var locationList = mutableListOf<ForecastLocation>()
        var attributeIndex = 0

        while(attributeIndex < parser.attributeCount){
            name = parser.getAttributeValue(attributeIndex)
            attributeIndex ++
        }

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }

            if (parser.name == "location") {
                locationList.add(readForecastLocation(parser))
            }

            attributeIndex += 1
        }
        return ForecastType(name, locationList)
    }

    //For reading ForecastLocation objects
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readForecastLocation(parser: XmlPullParser): ForecastLocation {
        parser.require(XmlPullParser.START_TAG, ns, "location")
        var name = ""
        var id = ""
        var content = ""
        var attributeIndex = 0

        while(attributeIndex < parser.attributeCount){
            when(parser.getAttributeName(attributeIndex)){
                "name" ->   name = parser.getAttributeValue(attributeIndex)
                "id" ->     id = parser.getAttributeValue(attributeIndex)
            }
            attributeIndex ++
        }

        while (parser.next() != XmlPullParser.END_TAG) {

            if(parser.eventType == XmlPullParser.TEXT) content = parser.text

        }


        return ForecastLocation(name, id, content)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}