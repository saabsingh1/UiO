package com.example.projectpreliminary

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.projectpreliminary.databinding.ActivityShowWeatherBinding
import com.example.projectpreliminary.viewmodel.ShowWeatherViewModel


class ShowWeather : AppCompatActivity(), LocationListener {

    private val viewModel : ShowWeatherViewModel by viewModels()
    private lateinit var locationManager: LocationManager
    private lateinit var binding: ActivityShowWeatherBinding
    private lateinit var viewPager: ViewPager2
    private val locationPermissionCode = 2
    private var defaultSearchCityName = ""
    private var cityNameSearchFor = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowWeatherBinding.inflate(layoutInflater)

        //Removes the automatic pop-up keyboard at the start of app
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        setContentView(binding.root)
        setSearchButton()

        // Instantiate a ViewPager2 and a PagerAdapter
        viewPager = binding.viewPager2

        val dontAskAgain = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)

        //When permission
        //  has not been asked for yet
        //  permission has been granted
        //  or don't ask again-box has been marked
        if(!dontAskAgain){
            getLocationPermission()
        }

        //When permission has already been denied
        else {
            defaultSearchCityName = "Oslo"
            searchForCityName(defaultSearchCityName)
        }

        //Initialize dataset into recycler and load parties
        setViewModelObserver()
    }

    //Checking for permission to get location data
    private fun getLocationPermission() {
        val checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionNotGrantedYet = (checkPermission != PackageManager.PERMISSION_GRANTED)

        if (permissionNotGrantedYet) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        } else {

            //When permission is granted, calls the function that requests update location from locationManager
            getLocationData()
        }
    }

    //This method is only called after permission has been received
    //therefore lint for MissingPermission has been suppressed
    @SuppressLint("MissingPermission")
    private fun getLocationData(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location : Location?
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        //When the device has a last known location cached, will use this to directly change location
        if(location != null){
            onLocationChanged(location)
        }

        //Otherwise we must request a location update from the device
        else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, this)

        }
    }

    //When location is updated after request
    override fun onLocationChanged(location: Location) {

        val latitude = location.latitude
        val longitude = location.longitude

        val geocoder = Geocoder(this)
        val cityName = getCityName(geocoder, latitude, longitude)

        cityNameSearchFor = cityName

        viewModel.loadData(arrayListOf(latitude.toFloat(), longitude.toFloat()))
    }

    //Empty override for deprecated function with API 29 or above
    @Deprecated("Deprecated in Java")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        //super.onStatusChanged(provider, status, extras)
    }

    //This method is only called after permission has been received
    //therefore lint for MissingPermission has been suppressed
    @SuppressLint("MissingPermission")
    override fun onProviderDisabled(provider: String) {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location : Location?
        location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)


        if(location != null){
            onLocationChanged(location)
        } else {
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0f, this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        println("THIS IS ONREQUESTPERMISSIONRESULTS")
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationData()
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                //TEMPRORARY CODE
                defaultSearchCityName = "Oslo"
                searchForCityName(defaultSearchCityName)
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCityName(
        geocoder: Geocoder,
        latitude: Double,
        longitude: Double
    ): String {
        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
        val cityName: String = addresses[0].adminArea //Locality null when using physical device, because I am in a subarea of Oslo? Changed to adminArea
        return cityName
    }

    //Sets functionality for search button
    private fun setSearchButton(){

        //For each time a new item is selected
        binding.searchButton.setOnClickListener {
            searchForCityName(binding.citynameSearch.text.toString())
            binding.citynameSearch.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }
    }

    //Initiate the search supplied with a city name
    private fun searchForCityName(cityName : String){
        val selectedItem = cityName


        //Updates item received from API
        val coordinates = getCoordinates(selectedItem)

        if(coordinates.isNotEmpty()){
            viewModel.loadData(coordinates)
        } else {
            if(isConnectingToInternet()){
                toastyFun("Input a valid city name") //When the input is invalid
            } else {
                toastyFun("No internet") //When there is no internet
            }
        }
    }

    //When getting coordinates by city name from search field
    private fun getCoordinates(cityName : String) : ArrayList<Float>{
        val arrListForReturn = arrayListOf<Float>()

        //Get coordinates from city name in a Geocoder
        val geocoder = Geocoder(this)

        try{
            val address = geocoder.getFromLocationName(cityName, 1)

            if(address.size != 0){
                val location = address.get(0)
                arrListForReturn.add(location.latitude.toFloat())
                arrListForReturn.add(location.longitude.toFloat())
            }
        } catch (e : Exception){
            Log.d("Exception while getting location from Cityname search: ", e.toString())
            System.runFinalization()
        }

        return arrListForReturn
    }

    private fun isConnectingToInternet(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetwork
        return if (ni == null) {
            // There are no active networks.
            toastyFun("no internet")
            false
        } else true
    }

    //For displaying toasts when necessary
    private fun toastyFun(toastText : String){
        val toast = Toast.makeText(
            this,
            toastText,
            Toast.LENGTH_SHORT
        )
        toast.show()
    }

    private fun setViewModelObserver(){
        //Initialize dataset into recycler and load parties
        viewModel.getData().observe(this){

            if(it == null){
                toastyFun("Failed to retrieve anything")
            } else {

                var cityName = "Oslo" //Default city value

                //Decide to get city name from either EditText or hidden interface
                if(binding.citynameSearch.text.toString() == ""){
                    if(defaultSearchCityName == "") cityName = cityNameSearchFor
                    else cityName = defaultSearchCityName
                } else {
                    cityName = binding.citynameSearch.text.toString()
                }

                // The pager adapter, which provides the pages to the view pager widget.
                // viewPager does not have a layoutManager

                val pagerAdapter = ShowWeatherViewPagerAdapter(this, it, cityName)
                viewPager.adapter = pagerAdapter

            }
        }
    }

}