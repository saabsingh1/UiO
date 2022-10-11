package com.example.projectpreliminary

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"
private const val ARG_PARAM7 = "param7"
private const val ARG_PARAM8 = "param8"
private const val ARG_PARAM9 = "param9"
private const val ARG_PARAM10 = "param10"
private const val ARG_PARAM11 = "param11"

/**
 * A simple [Fragment] subclass.
 * Use the [SevenDaysReport.newInstance] factory method to
 * create an instance of this fragment.
 */
class SevenDaysReport : Fragment() {
    // TODO: Rename and change types of parameters
    private var dayName         : String? = null
    private var tempMin         : String? = null
    private var tempMax         : String? = null
    private var windSpdAvrg     : String? = null
    private var precAvrg        : String? = null
    private var symbolMorning   : String? = null
    private var symbolAfternoon : String? = null
    private var symbolEvening   : String? = null
    private var symbolNight     : String? = null
    private var textColor       : String? = null
    private var bgColor         : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dayName         = it.getString(ARG_PARAM1)
            tempMin         = it.getString(ARG_PARAM2)
            tempMax         = it.getString(ARG_PARAM3)
            windSpdAvrg     = it.getString(ARG_PARAM4)
            precAvrg        = it.getString(ARG_PARAM5)
            symbolMorning   = it.getString(ARG_PARAM6)
            symbolAfternoon = it.getString(ARG_PARAM7)
            symbolEvening   = it.getString(ARG_PARAM8)
            symbolNight     = it.getString(ARG_PARAM9)
            textColor       = it.getString(ARG_PARAM10)
            bgColor         = it.getString(ARG_PARAM11)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inf = inflater.inflate(R.layout.fragment_seven_days_report, container, false)

        println("THIS IS TEXT COLOR $textColor")
        val tv1 = inf.findViewById<TextView>(R.id.day)
        tv1.text = dayName
        tv1.setTextColor(Color.parseColor(textColor))

        val tv2 = inf.findViewById<TextView>(R.id.tempMinView)
        tv2.text = tempMin
        tv2.setTextColor(Color.parseColor(textColor))

        val tv3 = inf.findViewById<TextView>(R.id.tempMaxView)
        tv3.text = tempMax
        tv3.setTextColor(Color.parseColor(textColor))

        val tv4 = inf.findViewById<TextView>(R.id.windAverage)
        tv4.text = "$windSpdAvrg m/s"
        tv4.setTextColor(Color.parseColor(textColor))

        val tv5 = inf.findViewById<TextView>(R.id.precAverage)
        tv5.text = "$precAvrg mm"
        tv5.setTextColor(Color.parseColor(textColor))
        val morgen = inf.findViewById<ImageView>(R.id.morgen)
        val ettermiddag = inf.findViewById<ImageView>(R.id.ettermiddag)
        val kveld = inf.findViewById<ImageView>(R.id.kveld)
        val natt = inf.findViewById<ImageView>(R.id.natt)

        val tv6 = inf.findViewById<ImageView>(R.id.windImageView)
        tv6.drawable.setTint(Color.parseColor(textColor))

        val tv7 = inf.findViewById<ImageView>(R.id.precipitationImageView)
        tv7.drawable.setTint(Color.parseColor(textColor))

        val names = arrayListOf("$symbolMorning", "$symbolAfternoon", "$symbolEvening", "$symbolNight")
        val views = arrayListOf(morgen, ettermiddag, kveld, natt)

        val list = arrayListOf<String>()

        for((index, element) in names.withIndex()){
            val id = requireContext().resources.getIdentifier(
                element, "drawable",
                requireContext().packageName
            )

            println("THIS IS NAME: $element")
            println("THIS IS ID: $id")
            val weatherToShow = element.split("_").toString()
            val drawable = requireContext().resources.getDrawable(id, null)
            views[index].setImageDrawable(drawable)
            list.add(views[index].contentDescription.toString())
            list.add(weatherToShow)
            list.add("..")
        }

        val tvBackground = inf.findViewById<TextView>(R.id.backGroundColor)

        println("THIS IS BACKGROUND COLOR: $bgColor")
        tvBackground.setBackgroundColor(Color.parseColor(bgColor))

        val layoutContainer = inf.findViewById<ConstraintLayout>(R.id.sevenDaysLayout)
        layoutContainer.contentDescription =
            "Current day, showing weather for $dayName ." +
                    "Minimum temperature is $tempMin ." +
                    "Maximum temperature is $tempMax ." +
                    "wind. $windSpdAvrg meters per second." +
                    "precipitation amount. $precAvrg millimeters." +
                    list.toString()

        return inf
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param dayName           Parameter 1.
         * @param tempMin           Parameter 2.
         * @param tempMax           Parameter 3.
         * @param windSpdAvrg       Parameter 4.
         * @param precAvrg          Parameter 5.
         * @param symbolMorning     Parameter 6.
         * @param symbolAfternoon   Parameter 7.
         * @param symbolEvening     Parameter 8.
         * @param symbolNight       Parameter 9.
         * @param textColor         Parameter 10.
         * @param bgColor           Parameter 11.
         * @return A new instance of fragment SevenDaysReport.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(dayName : String, tempMin : String, tempMax : String,
                        windSpdAvrg : String, precAvrg : String, symbolMorning : String,
                        symbolAfternoon : String, symbolEvening : String, symbolNight : String,
                        textColor : String, bgColor : String) =
            SevenDaysReport().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, dayName)
                    putString(ARG_PARAM2, tempMin)
                    putString(ARG_PARAM3, tempMax)
                    putString(ARG_PARAM4, windSpdAvrg)
                    putString(ARG_PARAM5, precAvrg)
                    putString(ARG_PARAM6, symbolMorning)
                    putString(ARG_PARAM7, symbolAfternoon)
                    putString(ARG_PARAM8, symbolEvening)
                    putString(ARG_PARAM9, symbolNight)
                    putString(ARG_PARAM10, textColor)
                    putString(ARG_PARAM11, bgColor)
                }
            }
    }
}