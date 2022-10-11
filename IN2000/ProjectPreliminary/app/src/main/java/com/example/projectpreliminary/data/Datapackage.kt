package com.example.projectpreliminary.data

data class Datapackage(
    var currentDayDoublesArrayList      : ArrayList<Double>,
    var restOfTheWeekStringArrayList    : ArrayList<ArrayList<String>>,
    var units                           : ArrayList<String>,
    var dayNames                        : ArrayList<String>,
    var symbolCodes                     : ArrayList<ArrayList<String>>,
    var backgroundColorCodes            : ArrayList<String>,
    var textColorCodes                  : ArrayList<String>
)
