package com.example.projectpreliminary

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class WeatherButton : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_weather_button, container, false)
        val buttonW = view.findViewById<View>(R.id.buttonWeather)
        buttonW.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        val intent = Intent(activity, ShowWeather::class.java)
        startActivity(intent)
    }
}