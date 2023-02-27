package com.utad.el_tiempo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.utad.el_tiempo.adapters.PrincipalFragment


class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    private var latitud = 0.0
    private var longitud = 0.0

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLocation()

        val location: Location? = if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }


        supportActionBar?.title = "El tiempo"
        supportActionBar?.setDisplayShowTitleEnabled(true);
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF03DAC5")))

        val fragment = PrincipalFragment()
        val bundle = Bundle()


        if (location != null) {
            longitud = location.longitude
            latitud = location.latitude
        }

        Log.i("main",latitud.toString())
        bundle.putDouble("latitud", latitud)
        bundle.putDouble("longitud", longitud)

        fragment.arguments = bundle

        if (locationManager.isLocationEnabled) {
            supportFragmentManager?.beginTransaction()
                ?.replace(R.id.conteiner, fragment)?.addToBackStack(null)?.commit()
        }
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)

    }


    override fun onLocationChanged(location: Location) {
        latitud = location.latitude
        longitud = location.longitude
    }
}


