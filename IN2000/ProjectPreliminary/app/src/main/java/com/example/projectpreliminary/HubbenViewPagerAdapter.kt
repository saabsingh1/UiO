package com.example.projectpreliminary

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HubbenViewPagerAdapter(fm: FragmentActivity): FragmentStateAdapter(fm) {

    private val count = 2
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {
        return WeatherButton()
        //return when(position) {
            //0 -> WeatherButton()
            //1 -> RuterButtonTemp()
            //else -> WeatherButton()
        //}

    }
}