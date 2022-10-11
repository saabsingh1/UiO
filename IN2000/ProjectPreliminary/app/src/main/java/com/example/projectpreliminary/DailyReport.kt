package com.example.projectpreliminary

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"
private const val ARG_PARAM7 = "param7"

/**
 * A simple [Fragment] subclass.
 * Use the [DailyReport.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyReport : Fragment() {
    private var temp:       String? = null
    private var wind:       String? = null
    private var city:       String? = null
    private var prec:       String? = null
    private var icon:       String? = null
    private var textColor:  String? = null
    private var bgColor:    String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            temp        = it.getString(ARG_PARAM1)
            wind        = it.getString(ARG_PARAM2)
            city        = it.getString(ARG_PARAM3)
            prec        = it.getString(ARG_PARAM4)
            icon        = it.getString(ARG_PARAM5)
            textColor   = it.getString(ARG_PARAM6)
            bgColor     = it.getString(ARG_PARAM7)
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Write type of weather


        // Inflate the layout for this fragment
        val inf = inflater.inflate(R.layout.fragment_daily_report, container, false)

        val tv1 = inf.findViewById<TextView>(R.id.temperatureView)
        tv1.text = temp
        tv1.setTextColor(Color.parseColor(textColor))

        val tv2 = inf.findViewById<TextView>(R.id.windView)
        tv2.text = "$wind m/s"
        tv2.setTextColor(Color.parseColor(textColor))

        val tv3 = inf.findViewById<TextView>(R.id.cityDeclaration)
        tv3.text = city
        tv3.setTextColor(Color.parseColor(textColor))

        val tv4 = inf.findViewById<TextView>(R.id.precipitationView)
        tv4.text = "$prec mm"
        tv4.setTextColor(Color.parseColor(textColor))

        val tv5 = inf.findViewById<ImageView>(R.id.iconView)

        val name = "$icon"
        val weather = "$icon".split("_")
        val weather_to_show = weather.toString()

        val id = requireContext().resources.getIdentifier(
            name, "drawable",
            requireContext().packageName
        )

        val drawable = requireContext().resources.getDrawable(id, null)
        tv5.setImageDrawable(drawable)

        val tv6 = inf.findViewById<ImageView>(R.id.windImageView)
        tv6.drawable.setTint(Color.parseColor(textColor))

        val tv7 = inf.findViewById<ImageView>(R.id.precipitationImageView)
        tv7.drawable.setTint(Color.parseColor(textColor))

        val tvBackground = inf.findViewById<TextView>(R.id.backGroundColor)
        tvBackground.setBackgroundColor(Color.parseColor(bgColor))

        val layoutContainer = inf.findViewById<ConstraintLayout>(R.id.currentDayLayout)
        layoutContainer.contentDescription =
                "Current day, showing weather for $city ." +
                "The weather today is $weather_to_show ." +
                "Temperature is $temp ." +
                "Wind.. $wind meters per second." +
                "precipitation.. amount $prec millimeters."

        return inf
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param temp      Parameter 1.
         * @param wind      Parameter 2.
         * @param city      Parameter 3.
         * @param prec      Parameter 4.
         * @param icon      Parameter 5.
         * @param textColor Parameter 6.
         * @param bgColor   Parameter 7.
         * @return A new instance of fragment DailyReport.
         */

        @JvmStatic
        fun newInstance(temp: String, wind: String, city: String, prec: String, icon: String, textColor: String, bgColor : String) =
            DailyReport().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, temp)
                    putString(ARG_PARAM2, wind)
                    putString(ARG_PARAM3, city)
                    putString(ARG_PARAM4, prec)
                    putString(ARG_PARAM5, icon)
                    putString(ARG_PARAM6, textColor)
                    putString(ARG_PARAM7, bgColor)
                }
            }
    }
}