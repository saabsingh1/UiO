package com.example.projectpreliminary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpreliminary.ShowWeather
import com.example.projectpreliminary.data.Datapackage
import com.example.projectpreliminary.data.Datasource
import com.example.projectpreliminary.model.locationforecast.Locforecreceived
import com.example.projectpreliminary.model.nowcast.Nowcastreceived
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowWeatherViewModel : ViewModel() {

    private val dataSource = Datasource()
    private val retrievedDatapackage = MutableLiveData<Datapackage>()

    fun getData() : MutableLiveData<Datapackage> {
        return retrievedDatapackage
    }

    fun loadData(coordinates: ArrayList<Float>){
        viewModelScope.launch(Dispatchers.IO){
            dataSource.loadData(coordinates).also{
                retrievedDatapackage.postValue(it)
            }
        }
    }

}